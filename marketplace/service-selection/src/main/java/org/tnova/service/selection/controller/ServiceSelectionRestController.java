package org.tnova.service.selection.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.request.async.DeferredResult;
import org.tnova.service.selection.domain.AccountingRequest;
import org.tnova.service.selection.domain.NetworkServiceActivationReply;
import org.tnova.service.selection.domain.NetworkServiceActivationRequest;
import org.tnova.service.selection.domain.instantiate.NetworkServiceInstantiateReply;
import org.tnova.service.selection.domain.nsd.NetworkService;
import org.tnova.service.selection.exceptions.*;
import org.tnova.service.selection.service.NetworkCatalogService;
import org.tnova.service.selection.service.ServiceSelectionService;
import org.tnova.service.selection.tasks.NetworkServiceActivationTask;
import org.tnova.service.selection.tasks.ServiceSelectionMsgTable;
import org.tnova.service.selection.tasks.ServiceSelectionRequestMsgTable;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping( "service/selection")
public class ServiceSelectionRestController
{
    private static final Logger logger = LoggerFactory.getLogger( ServiceSelectionRestController.class );

    private static OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
    private static final AtomicInteger lastRequestId = new AtomicInteger( 1 );
    private static final AtomicLong concurrentRequests = new AtomicLong( 1 );
    private Timer timer = new Timer();

    private ServiceSelectionService serviceSelection;

    @Autowired
    private NetworkCatalogService networkCatalogService;

    @Autowired
    public ServiceSelectionRestController( ServiceSelectionService serviceSelection )
    {
        this.serviceSelection = serviceSelection;
    }

    @RequestMapping( method = RequestMethod.POST, value = "/blocking" )
    public NetworkServiceActivationReply activateNetworkService( @RequestBody final NetworkServiceActivationRequest request )
    {
        logger.info( "Activate Network Service request {}", request.toString() );
        NetworkServiceActivationReply reply = serviceSelection.activateNetworkService( request );

        if( reply == null )
        {
            logger.error( "An unexpected error occurred during the request !!!" );
            throw new ServiceSelectionBadRequestException( "An unexpected error occurred during the request" );
        }

        logger.info( "Publishing to Accounting Module for Billing reasons" );

        boolean result = serviceSelection.publishingToAccounting(
            networkCatalogService.getNetworkServiceByNsdId( request.getNsId() ), reply,
            request );

        if ( result )
        {
            logger.info( "Publishing to accounting successfully completed ... " );
        }
        else
        {
            logger.error( "An unexpected error occurred during the publishing to accounting !!!" );
            throw new ServiceSelectionBadRequestException( "An unexpected error occurred during the publishing to accounting. Please check" );
        }

        return reply;
    }

    @RequestMapping( method = RequestMethod.POST, value = "/{id}" )
    public HttpStatus callbackAsyncOrchertator( @PathVariable String id,
        @RequestBody final NetworkServiceInstantiateReply response )
    {
        boolean success = false;
        logger.info( "Receiving asynchronous reply from orchestrator with id={}", id );
        if ( ServiceSelectionMsgTable.getInstance().containsKey( Integer.parseInt( id ) ) )
        {
            logger.info( "Request was sucessfully received for existing entry, reply={}", response );

            ObjectMapper mapper = new ObjectMapper();
            try {
                logger.info( mapper.writerWithDefaultPrettyPrinter().writeValueAsString( response ) );
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }

            ServiceSelectionMsgTable.getInstance().add( Integer.parseInt( id  ), response );

            NetworkService networkService = networkCatalogService.getNetworkServiceByNsdId( response.getNsdId() );
            if( networkService != null )
            {
                logger.info( "Network service with id {} found in business service catalog", response.getNsdId() );
            }

            NetworkServiceActivationRequest request = ServiceSelectionRequestMsgTable.getInstance().get( Integer.parseInt( id ) );
            if ( request != null )
            {
                logger.info( "Network Service Request found with id{} in RequestMessageTable" );
            }


            if ( response.getStatus() != null && response.getStatus().equalsIgnoreCase( "INSTANTIATED" ))
            {
                logger.info( "NS instantiated successfully with id = {}", response.getId() );
                success = serviceSelection.publishingToAccountingAsync( networkService, response, request );

            }


            if ( success )
            {
                logger.info( "Sucesfull trigger to accounting for ns instance id={}", response.getId() );
            }
            else
            {
                logger.info( "Failed trigger to accounting for ns instance id={} check the logs", response.getId() );
            }

            return HttpStatus.OK;

        }
        else {
            logger.error( "Unknown Request with id={} from orchestrator with body={}", id, response.toString() );
            throw new ServiceSelectionNotFoundException( "Unknown Request with id=" + id );
        }
    }

    @RequestMapping( method = RequestMethod.POST )
    public DeferredResult<NetworkServiceInstantiateReply> activateNetworkServiceAsync( @RequestBody final NetworkServiceActivationRequest request )
    {
        int reqId = lastRequestId.getAndIncrement();
        long concReqs = concurrentRequests.getAndIncrement();

        String result = null;
        logger.info( "{}: Start non-blocking request #{}, processing time: {}ms", concReqs, reqId );
        DeferredResult<NetworkServiceInstantiateReply> deferredResult = new DeferredResult<>( 900000);

        ServiceSelectionMsgTable.getInstance().add( reqId, new NetworkServiceInstantiateReply() );
        ServiceSelectionRequestMsgTable.getInstance().add( reqId, request );

        try
        {
            result = serviceSelection.activateNetworkServiceAsync( request, reqId );
        }
        catch( Exception ex )
        {
            logger.error( "An exception had occurred during. Check !!!!" );
        }

        if ( result == null )
        {
            logger.error( "En error during communication service instantiation for for NSD id = {}", request.getNsId() );
            ServiceSelectionMsgTable.getInstance().remove( reqId );

            throw
                new ServiceSelectionBadRequestException( "En error occured during instantiation of the service. Please check!!!" );

        }

        NetworkServiceActivationTask task
            = new NetworkServiceActivationTask( reqId, concurrentRequests, deferredResult );


        // Schedule the task for a-synch completion in the future
        timer.schedule(task, 5000, 5000);

        logger.debug("{}: Processing of non-blocking request #{} leave the request thread", concReqs, reqId);


        return deferredResult;
    }

    @RequestMapping( method = RequestMethod.GET )
    public List<NetworkServiceInstantiateReply> getAllNetworkServiceInstances()
    {
        logger.info( "Retrieve all ns instances from orchestrator" );
        List<NetworkServiceInstantiateReply> nsInstances = serviceSelection.getAllNsInstances();

        if ( nsInstances != null )
        {
            logger.info( "{} network instances found in orchestrator.", nsInstances.size() );
        }

        ObjectMapper mapper = new ObjectMapper();
        try
        {
            mapper.writerWithDefaultPrettyPrinter().writeValueAsString( nsInstances );
        } catch( Exception ex )
        {
            ex.printStackTrace();
        }


        return nsInstances;
    }

    @RequestMapping( method = RequestMethod.GET, value = "/{id}")
    public NetworkServiceInstantiateReply getNetworkInstanceById( @PathVariable String id )
    {
        logger.info( "Retrieve ns instance from orchestrator with id=<{}>", id );
        NetworkServiceInstantiateReply nsInstances = serviceSelection.getNsInstanceById( id );

//        if ( nsInstances != null )
//        {
//            logger.info( "{} network instances found in orchestrator.", nsInstances.size() );
//        }

        ObjectMapper mapper = new ObjectMapper();
        try
        {
            mapper.writerWithDefaultPrettyPrinter().writeValueAsString( nsInstances );
        } catch( Exception ex )
        {
            ex.printStackTrace();
        }


        return nsInstances;
    }

    @RequestMapping( method = RequestMethod.GET, value = "/{id}/status" )
    public String getNetworkServiceInstanceStatus( @PathVariable String id )
    {
        return serviceSelection.getNetworkInstanceStatus( id );
    }

    @RequestMapping( method = RequestMethod.GET, value = "/{id}/terminate" )
    public HttpStatus terminateNetworkService( @PathVariable( "id") String id )
    {
        logger.info( "Terminate a network instance with id={}", id );

        if ( id == null || id.equalsIgnoreCase( "" ) )
        {
            logger.error( "NS instance id is not valid. Please check" );
            throw new ServiceSelectionBadRequestException( "Invalid NS instance id" );
        }

        HttpStatus httpStatus = serviceSelection.terminateNetworkService( id );

        return httpStatus;

    }

    @RequestMapping( method = RequestMethod.GET, value = "/{id}/stop" )
    @ResponseStatus( HttpStatus.OK )
    public HttpStatus stopNetworkService( @PathVariable( "id") String id )
    {
        logger.info( "Stop a network instance with id={}", id );

        if ( id == null || id.equalsIgnoreCase( "" ) )
        {
            logger.error( "NS instance id is not valid. Please check" );
            throw new ServiceSelectionBadRequestException( "Invalid NS instance id" );
        }
        HttpStatus httpStatus = serviceSelection.stopNetworkService( id );

        return httpStatus;
    }

    @RequestMapping( method = RequestMethod.GET, value = "/accounting")
    public List<AccountingRequest> getAccountingRequests()
    {
        logger.info( "Retrieve all accounting requests" );

        return serviceSelection.getAccountingRequests();
    }

    @RequestMapping( method = RequestMethod.GET, value = "/accounting/{id}")
    public List<AccountingRequest> getAccountingRequestsByInstanceId( @PathVariable( "id" ) String id)
    {
        logger.info( "Retrieve all accounting requests" );

        return serviceSelection.getAccountingRequestByNetworkInstance( id );
    }

    @ExceptionHandler
    @ResponseStatus( HttpStatus.NOT_FOUND)
    public ErrorMessage handleNetworkServiceInstanceNotFound( NetworkServiceInstanceNotFound ex )
    {
        return new ErrorMessage( HttpStatus.NOT_FOUND.value(), ex.getMessage() );
    }

    @ExceptionHandler
    @ResponseStatus( HttpStatus.NOT_FOUND )
    public String handleServiceSelectionNotFoundException( ServiceSelectionNotFoundException ex ) throws
        Exception
    {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString( new ErrorMessage( HttpStatus.NOT_FOUND.value(), ex.getMessage() ) );
    }

    @ExceptionHandler
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public String handleNsInstancesEmptyListException( NsInstancesEmptyListException ex )
    {
        return ex.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    public ErrorMessage handleServiceSelectionBadRequestException( ServiceSelectionBadRequestException ex )
    {
        return new ErrorMessage( HttpStatus.BAD_REQUEST.value(), ex.getMessage() );
    }
}
