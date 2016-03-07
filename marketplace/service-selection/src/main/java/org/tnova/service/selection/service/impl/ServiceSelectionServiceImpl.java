package org.tnova.service.selection.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.tnova.service.selection.domain.AccountingRequest;
import org.tnova.service.selection.domain.NetworkServiceActivationReply;
import org.tnova.service.selection.domain.NetworkServiceActivationRequest;
import org.tnova.service.selection.domain.Vnf;
import org.tnova.service.selection.domain.instantiate.NetworkServiceInstantiateReply;
import org.tnova.service.selection.domain.nsd.NetworkService;
import org.tnova.service.selection.domain.nsd.Sla;
import org.tnova.service.selection.domain.vnf.VnfDescriptor;
import org.tnova.service.selection.exceptions.*;
import org.tnova.service.selection.service.NetworkCatalogService;
import org.tnova.service.selection.service.ServiceSelectionService;
import org.tnova.service.selection.service.VnfService;
import org.tnova.service.selection.tasks.AccountingRequestMsgTable;
import org.tnova.service.selection.util.Helpers;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ServiceSelectionServiceImpl
    implements ServiceSelectionService
{
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger( ServiceSelectionServiceImpl.class );

    @Value( "${tnova.orchestrator.host}" )
    private String orchestratorUrl;

    @Value( "${tnova.accounting.module.host}" )
    private String accountingUrl;

    @Value( "${tnova.accounting.module.feature.status}" )
    private String isAccountingModuleEnabled;

    @Value( "${tnova.orchestrator.module.feature.status}" )
    private String isOrchestratorModuleEnabled;

    @Value( "${tnova.marketplace.callback.url}" )
    private String marketplaceCallbackUrl;

    @Value( "${tnova.vnf.feature.status}" )
    private String isVnfStoreEnabled;

    @Value( "${tnova.vnf.module.host}" )
    private String vnfStoreUrl;

    @Autowired
    private NetworkCatalogService networkCatalogService;

    @Autowired
    private VnfService vnfService;

    @Override
    public boolean publishingToAccountingAsync( NetworkService networkService, NetworkServiceInstantiateReply reply,
        NetworkServiceActivationRequest request )
    {
        ObjectMapper mapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();

        logger.info(
            "Publishing NetworkService to accounting module, for network service with id={} and ns instanse with id={}",
            networkService.getNsd().getId(), reply.getId() );
        boolean success = false;

        List<AccountingRequest> accountingRequests = new ArrayList<>();
        List<AccountingRequest> updatedRequests = new ArrayList<>();

        logger.info( "Creating an accounting request for network service with id={}", networkService.getNsd().getId() );

        AccountingRequest serviceReq = Helpers
            .createAccountingRequestForNetworkService( networkService, reply, request );

        if( serviceReq != null )
        {
            accountingRequests.add( serviceReq );
        }

        logger.info( "{} found in order to be published in Accounting module. Start sending.... ",
            accountingRequests.size() );

        for( AccountingRequest accountingRequest : accountingRequests )
        {
            if( isAccountingModuleEnabled.equalsIgnoreCase( "disabled" ) )
            {
                logger.info( "Accounting Module is disabled. Printing the requests in json format" );
                try
                {
                    logger.info( mapper.writerWithDefaultPrettyPrinter().writeValueAsString( accountingRequest ) );

                }
                catch( Exception ex )
                {
                    ex.printStackTrace();
                }
                accountingRequest.setAdditionalProperty( "is_published", true );
                updatedRequests.add( accountingRequest );

                success = true;

            }
            else
            {
                try
                {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType( MediaType.APPLICATION_JSON );
                    HttpEntity<String> entity = new HttpEntity<>( mapper.writeValueAsString( accountingRequest ),
                        headers );

                    logger.info( "Json Request is published to accounting. Printing request" );
                    logger.info( mapper.writerWithDefaultPrettyPrinter().writeValueAsString( accountingRequest ) );

                    ResponseEntity<String> response = restTemplate
                        .exchange( accountingUrl, HttpMethod.POST, entity, String.class );

                    if( response.getStatusCode() != HttpStatus.CREATED )
                    {

                        logger.error( "Issue during publishing to accounting request, "
                                + "with http code = {}, body={}, for request = {}",
                            response.getStatusCode().getReasonPhrase(), response.getBody(),
                            mapper.writerWithDefaultPrettyPrinter().writeValueAsString( accountingRequest ) );

                        return false;
                    }

                    logger.info( "Successful publishing to Accounting Request" );
                    accountingRequest.setAdditionalProperty( "is_published", true );

                    success = true;

                }

                catch( Exception ex )
                {
                    ex.printStackTrace();
                }
            }
        }

        AccountingRequestMsgTable.getInstance().add( reply.getId(), updatedRequests );
        return success;
    }

    @Override
    public String activateNetworkServiceAsync( NetworkServiceActivationRequest request, int requestId ) throws Exception
    {
        logger.info( "Sending request to orchestrator to activate the service .. !!" );
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );

        NetworkService networkService = networkCatalogService.getNetworkServiceByNsdId( request.getNsId() );

        if( networkService == null )
        {
            logger.info( "No available service exists in BSc with nsd_id = {}", request.getNsId() );

            String errorMessage = "No network service available with id=" + request.getNsId();
            throw new ServiceSelectionBadRequestException( errorMessage );
        }

        if( isOrchestratorModuleEnabled.equalsIgnoreCase( "enabled" ) )
        {
            logger.info( "Network Service found in BSc with nsd_id = {}", networkService.getNsd().getId() );

            String mrkPlaceCallbackUri = marketplaceCallbackUrl + "/" + requestId;
            logger.debug( "Constructing the callback uri={}", mrkPlaceCallbackUri );
            NetworkServiceActivationRequest requestToOrch = new NetworkServiceActivationRequest();

            for( Sla sla : networkService.getNsd().getSla() )
            {
                if( sla.getId().equalsIgnoreCase( request.getFlavorId() ) )
                {
                    requestToOrch.setAdditionalProperty( "flavour", sla.getSlaKey() );
                }
                else
                {
                    logger.info( "Sla Key not found for flavorID = {}. Using flavorId...", request.getFlavorId() );
                    requestToOrch.setAdditionalProperty( "flavour", request.getFlavorId() );
                }
            }

            requestToOrch.setCallbackUrl( mrkPlaceCallbackUri );
            requestToOrch.setCustomerId( request.getCustomerId() );
            requestToOrch.setNsId( request.getNsId() );
            requestToOrch.setNapId( request.getNapId() );

            ObjectMapper mapper = new ObjectMapper();
            HttpEntity<String> entity = new HttpEntity<>( mapper.writeValueAsString( requestToOrch ), headers );

            ResponseEntity<String> responseEntity = restTemplate
                .exchange( orchestratorUrl, HttpMethod.POST, entity, String.class );

            if( responseEntity.getStatusCode() == HttpStatus.OK )
            {
                logger.info( "Request send it to orchestrator. Waiting for response" );
                return "SUCCESS";

            }
            else
            {
                return null;
            }
        }
        else
        {
            logger.info( "Mocking reply answer for request with id={}", requestId );
            NetworkServiceActivationReply reply = new NetworkServiceActivationReply( new Integer( 1 ),
                networkService.getNsd().getId(), "ACTIVATED", LocalDateTime.now().toString(),
                LocalDateTime.now().toString(), Arrays.asList( new Vnf( 1, "2", "47" ) ) );

            ObjectMapper mapper = new ObjectMapper();

            String replymessage = mapper.writeValueAsString( reply );

            return replymessage;
        }
    }

    @Override
    public boolean publishingToAccounting( NetworkService networkService, NetworkServiceActivationReply reply,
        NetworkServiceActivationRequest request )
    {
        RestTemplate restTemplate = new RestTemplate();
        List<AccountingRequest> accountingRequests = new ArrayList<>();
        logger.info( "Creating Accounting Request for Service for id={}", reply.getId() );

        AccountingRequest serviceRequest = new AccountingRequest();
        String vnfReferenceIdForTest = null;
        String vnfReferenceFlavorId = null;

        for( Sla sla : networkService.getNsd().getSla() )
        {
            if( sla.getId().equalsIgnoreCase( request.getFlavorId() ) )
            {
                serviceRequest.setBillingModel( sla.getBilling().getModel() );
                serviceRequest.setPeriodCost( sla.getBilling().getPrice().getPricePerPeriod() );
                serviceRequest.setPriceUnit( sla.getBilling().getPrice().getUnit() );
                serviceRequest.setSetupCost( sla.getBilling().getPrice().getSetup() );
                serviceRequest.setFlavour( sla.getSlaKey() );
                vnfReferenceIdForTest = String.valueOf( sla.getConstituentVnf().get( 0 ).getVnfReference() );
                vnfReferenceFlavorId = String.valueOf( sla.getConstituentVnf().get( 0 ).getVnfFlavourIdReference() );
            }
        }

        serviceRequest.setInstanceId( reply.getId().toString() );
        serviceRequest.setProductId( networkService.getNsd().getId() );
        serviceRequest.setAgreementId( "ns" + reply.getId().toString() );
        List<String> relatives = new ArrayList<>();

        for( Vnf vnf : reply.getVnfs() )
        {
            relatives.add( vnf.getVnfdId() );
        }
        serviceRequest.setRelatives( relatives.toString().replace( "[", "" ).replace( "]", "" ) );
        serviceRequest.setProductType( "ns" );
        //        serviceRequest.setFlavour( request.getFlavorId() );
        serviceRequest.setProviderId( networkService.getNsd().getProviderId() );
        serviceRequest.setClientId( request.getCustomerId() );
        serviceRequest.setStatus( reply.getStatus() );
        serviceRequest.setPeriod( "P1M" );
        serviceRequest.setRenew( true );

        logger.info( "Adding service request to list of the requests to be sent into accounting" );
        accountingRequests.add( serviceRequest );

        logger.info( "Creating Accounting Request for Each VNF" );
        for( Vnf vnf : reply.getVnfs() )
        {
            AccountingRequest vnfRequest = new AccountingRequest();
            vnfRequest.setInstanceId( vnf.getVnfiId() );
            vnfRequest.setAgreementId( "vnf" + vnf.getVnfiId() );

            VnfDescriptor vnfDescriptor;
            if( isOrchestratorModuleEnabled.equalsIgnoreCase( "disabled" ) )
            {
                logger.warn( "Using a vnf from NSD only for testing reasons. Vnf Reference id={}",
                    vnfReferenceIdForTest );
                vnfDescriptor = vnfService.getVnfById( vnfReferenceIdForTest );
                vnfRequest.setProductId( vnfReferenceIdForTest );

            }
            else
            {
                vnfDescriptor = vnfService.getVnfById( vnf.getVnfdId() );
                vnfRequest.setProductId( vnf.getVnfdId() );

            }

            if( vnfDescriptor == null )
            {
                logger.error( "Unable to retrieve vnf descriptor. Check logs for possible errors" );

                return false;
            }

            vnfRequest.setProviderId( String.valueOf( vnfDescriptor.getProviderId() ) );
            vnfRequest.setClientId( networkService.getNsd().getProviderId() );
            vnfRequest.setStatus( "RUNNING" );
            vnfRequest.setBillingModel( vnfDescriptor.getBillingModel().getModel() );
            vnfRequest.setPeriodCost( vnfDescriptor.getBillingModel().getPrice().getMaxPerPeriod() );
            vnfRequest.setSetupCost( vnfDescriptor.getBillingModel().getPrice().getSetup() );
            vnfRequest.setPriceUnit( vnfDescriptor.getBillingModel().getPrice().getUnit() );
            vnfRequest.setProductType( "vnf" );

            vnfRequest.setPeriod( "P1M" );
            vnfRequest.setRelatives( networkService.getNsd().getId() );
            vnfRequest.setRenew( true );

            logger.info( "vnf reference id is = {}", vnfReferenceFlavorId );
            vnfRequest.setFlavour( vnfReferenceFlavorId );
            /*if( isOrchestratorModuleEnabled.equalsIgnoreCase( "disabled....." ) )
            {
                for( DeploymentFlavour deploymentFlavour : vnfDescriptor.getDeploymentFlavours() )
                {
                    if( deploymentFlavour.getId().equalsIgnoreCase( vnfReferenceFlavorId ) )
                    {
                        logger.info( "Flavor key for VNF with id={} is {}", vnfReferenceFlavorId,
                            deploymentFlavour.getFlavourKey() );
                        vnfRequest.setFlavour( deploymentFlavour.getFlavourKey() );
                    }
                }
            }

            else
            {
                for( Sla sla : networkService.getNsd().getSla() )
                {
                    if( sla.getId().equalsIgnoreCase( request.getFlavorId() ) )
                    {
                        List<ConstituentVnf> constiVnf = sla.getConstituentVnf();
                        for( ConstituentVnf constituentVnf : constiVnf )
                        {
                            if( Long.parseLong( vnf.getVnfdId() ) == constituentVnf.getVnfReference() )
                            {
                                vnfReferenceFlavorId = constituentVnf.getVnfFlavourIdReference();
                                vnfRequest.setFlavour( vnfReferenceFlavorId );
                                *//*for( DeploymentFlavour deploymentFlavour : vnfDescriptor.getDeploymentFlavours() )
                                {
                                    if( deploymentFlavour.getId().equalsIgnoreCase( vnfReferenceFlavorId ) )
                                    {
                                        logger.info( "Flavor key for VNF with id={} is {}", vnfReferenceFlavorId,
                                            deploymentFlavour.getFlavourKey() );
                                        vnfRequest.setFlavour( deploymentFlavour.getFlavourKey() );
                                    }
                                }*//*
                            }
                        }
                    }

                }
            }*/

            logger.info( "adding request ... " );
            accountingRequests.add( vnfRequest );
        }

        for( AccountingRequest accountingRequest : accountingRequests )
        {
            ObjectMapper mapper = new ObjectMapper();
            try
            {
                if( isAccountingModuleEnabled.equalsIgnoreCase( "disabled" ) )
                {
                    logger.info( "No communication is enabled with accounting. Printing each requests. " );
                    logger.info( mapper.writerWithDefaultPrettyPrinter().writeValueAsString( accountingRequest ) );
                }

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType( MediaType.APPLICATION_JSON );
                HttpEntity<String> entity = new HttpEntity<>( mapper.writeValueAsString( accountingRequest ), headers );

                logger.info( "Json Request is published to accounting" );
                logger.info( mapper.writerWithDefaultPrettyPrinter().writeValueAsString( accountingRequest ) );

                ResponseEntity<String> response = restTemplate
                    .exchange( accountingUrl, HttpMethod.POST, entity, String.class );
                if( response.getStatusCode() != HttpStatus.CREATED )
                {

                    logger.error( "Issue during publishing to accounting request, "
                            + "with http code = {}, body={}, for request = {}", response.getStatusCode().getReasonPhrase(),
                        response.getBody(),
                        mapper.writerWithDefaultPrettyPrinter().writeValueAsString( accountingRequest ) );
                    return false;
                }
            }
            catch( Exception ex )
            {

                ex.printStackTrace();
            }

        }

        return true;
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public List<NetworkServiceInstantiateReply> getAllNsInstances()
    {
        logger.info( "Retrieve all ns  instances from orchestrator" );
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        List<NetworkServiceInstantiateReply> instances = new ArrayList<>();

        if( isOrchestratorModuleEnabled != null && isOrchestratorModuleEnabled.equalsIgnoreCase( "disabled" ) )
        {
            logger.info( "No connection to Orchestrator. Empty content http status" );
            throw new NsInstancesEmptyListException( "No reply from orchestrator, empty list" );

        }

        final ResponseEntity<String> response = restTemplate.getForEntity( orchestratorUrl, String.class );

        if( response != null && response.getStatusCode() == HttpStatus.OK )
        {
            logger.info( "Received OK from orchestrator" );
            try
            {
                instances = mapper.readValue( response.getBody(), TypeFactory.defaultInstance()
                    .constructCollectionType( List.class, NetworkServiceInstantiateReply.class ) );

            }
            catch( Exception ex )
            {
                logger.error( "An exception occurred during retrieving ns instances from orchestrator" );
                logger.error( ex.getMessage() );
            }
        }

        if( instances.isEmpty() )
            throw new NsInstancesEmptyListException();

        return instances;
    }

    @Override
    public List<AccountingRequest> getAccountingRequests()
    {
        logger.info( "Get all accounting requests for all instantiated services" );

        List<AccountingRequest> requests = AccountingRequestMsgTable.getInstance().getAllAccountingRequests();

        if( requests == null || requests.isEmpty() )
        {
            logger.error( "No accounting requests. Return Empty List" );
            return new ArrayList<AccountingRequest>();
        }


        logger.info( "{} accounting request found", requests.size() );
        return requests;
    }

    @Override
    public List<AccountingRequest> getAccountingRequestByNetworkInstance( String instanceId )
    {
        logger.info( "Retrieve all accounting requests based on network instance id = {}", instanceId );

                List<AccountingRequest> requests = AccountingRequestMsgTable.getInstance().getAllAccountingRequests();

        if( requests == null || requests.isEmpty() )
        {
            logger.error( "No accounting requests. Return Empty List" );
            return new ArrayList<AccountingRequest>();
        }

        logger.info( "{} accounting request found", requests.size() );
        return requests;
    }

    @Override
    public NetworkServiceActivationReply activateNetworkService( NetworkServiceActivationRequest request )
    {
        logger.info( "Sending request to orchestrator to activate the service .. !!" );
        RestTemplate restTemplate = new RestTemplate();
        NetworkServiceActivationReply reply = null;

        NetworkService networkService = networkCatalogService.getNetworkServiceByNsdId( request.getNsId() );

        if( networkService == null )
        {
            logger.info( "No available service exists in BSc with nsd_id = {}", request.getNsId() );

            String errorMessage = "No network service available with id=" + request.getNsId();
            throw new ServiceSelectionBadRequestException( errorMessage );
        }

        logger.info( "Network Service found in BSc with nsd_id = {}", networkService.getNsd().getId() );


        reply = new NetworkServiceActivationReply( new Integer( 1 ), networkService.getNsd().getId(), "ACTIVATED",
            LocalDateTime.now().toString(), LocalDateTime.now().toString(), Arrays.asList( new Vnf( 1, "2", "47" ) ) );

        return reply;
    }

    @Override
    public HttpStatus stopNetworkService( String id )
    {
        logger.info( "Stopping a network service with id={}", id );

        if( isOrchestratorModuleEnabled != null && isOrchestratorModuleEnabled.equalsIgnoreCase( "disabled" ) )
        {
            logger.info( "No connection to Orchestrator. Reply is mocked as OK" );

            return HttpStatus.OK;
        }

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put( "id", id );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        HttpEntity<String> stopRequest = new HttpEntity<>( "", headers );

        final ResponseEntity<String> responseEntity = restTemplate
            .exchange( orchestratorUrl + "/{id}/stop", HttpMethod.PUT, stopRequest, String.class, params );

        if( responseEntity.getStatusCode() != HttpStatus.OK )
        {
            logger.error( "An error occured during stop service with id={} to orchestrator. "
                + "Response code from orchestrator is=", responseEntity.getStatusCode().getReasonPhrase() );

            return HttpStatus.BAD_REQUEST;
        }
        else
        {
            logger.info( "NS instance with id={} stopped from orchestrator", id );

            return responseEntity.getStatusCode();
        }
    }

    @Override
    public HttpStatus terminateNetworkService( String id )
    {
        HttpStatus status = HttpStatus.OK;
        logger.info( "Terminating a network service with id={}", id );

        if( isOrchestratorModuleEnabled != null && isOrchestratorModuleEnabled.equalsIgnoreCase( "disabled" ) )
        {
            logger.info( "No connection to Orchestrator. Reply is mocked as OK" );

            return HttpStatus.OK;
        }

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put( "id", id );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        HttpEntity<String> stopRequest = new HttpEntity<>( "", headers );

        try
        {
            final ResponseEntity<String> responseEntity = restTemplate
                .exchange( orchestratorUrl + "/{id}/terminate", HttpMethod.PUT, stopRequest, String.class, params );

            if( responseEntity.getStatusCode() != HttpStatus.OK )
            {
                logger.error( "An error occured during stop service with id={} to orchestrator. "
                    + "Response code from orchestrator is=", responseEntity.getStatusCode().getReasonPhrase() );

                status = responseEntity.getStatusCode();
            }
            else
            {
                logger.info( "NS instance with id={} terminated from orchestrator", id );

                status = responseEntity.getStatusCode();
            }
        }
        catch( HttpClientErrorException ex )
        {
            if( ex.getMessage().equalsIgnoreCase( "404 Not Found" ) )
            {
                status = HttpStatus.NOT_FOUND;
            }
        }

        return status;
    }

    @Override
    public String getNetworkInstanceStatus( String id )
    {

        HttpStatus status = HttpStatus.OK;
        final ResponseEntity<String> responseEntity;
        logger.info( "Retrieving the status of network service with id={}", id );

        if( isOrchestratorModuleEnabled != null && isOrchestratorModuleEnabled.equalsIgnoreCase( "disabled" ) )
        {
            logger.info( "No connection to Orchestrator. Reply is mocked as OK" );

            return "INIT";
        }

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put( "id", id );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        HttpEntity<String> stopRequest = new HttpEntity<>( "", headers );

        try
        {
            responseEntity = restTemplate
                .exchange( orchestratorUrl + "/{id}/status", HttpMethod.GET, stopRequest, String.class, params );

            if( responseEntity.getStatusCode() == HttpStatus.NOT_FOUND )
            {
                logger.info( "Network Instance with id={} not found", id );
                throw new NetworkServiceInstanceNotFound();
            }
            else if( responseEntity.getStatusCode() == HttpStatus.OK )
            {
                logger.info( "NS instance with id={} status is {}", id, responseEntity.getBody() );
            }
            else
            {
                logger.error(
                    "An error occured during retrieving the status of the  service with id={} to orchestrator. "
                        + "Response code from orchestrator is=", responseEntity.getStatusCode().getReasonPhrase() );
                throw new OrchestratorBadResponseException( "An error occurred during the request from orchestrator." );
            }

            return responseEntity.getBody();

        }
        catch( HttpClientErrorException ex )
        {
            if ( ex.getMessage().equalsIgnoreCase( "404 Not Found" ) )
            {
                throw new ServiceSelectionNotFoundException( "NS instance with id = " + id + " not found" );
            }
        }

        return null;

    }

}
