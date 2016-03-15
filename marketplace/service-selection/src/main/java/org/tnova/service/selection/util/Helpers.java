package org.tnova.service.selection.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tnova.service.selection.domain.AccountingRequest;
import org.tnova.service.selection.domain.NetworkServiceActivationRequest;
import org.tnova.service.selection.domain.instantiate.NetworkServiceInstantiateReply;
import org.tnova.service.selection.domain.instantiate.Start;
import org.tnova.service.selection.domain.instantiate.Vnfr;
import org.tnova.service.selection.domain.nsd.ConstituentVnfSla;
import org.tnova.service.selection.domain.nsd.NetworkService;
import org.tnova.service.selection.domain.nsd.Sla;
import org.tnova.service.selection.domain.vnf.BillingModel;
import org.tnova.service.selection.domain.vnf.Price;
import org.tnova.service.selection.domain.vnf.Vdu;
import org.tnova.service.selection.domain.vnf.VnfDescriptor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helpers
{
    private static final Logger logger = LoggerFactory.getLogger( Helpers.class );

    public VnfDescriptor createMockVnf()
    {
        VnfDescriptor vnfDescriptor = new VnfDescriptor();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure( JsonParser.Feature.ALLOW_COMMENTS, true );
        mapper.configure( JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true );
        try
        {
            vnfDescriptor = mapper.readValue( getClass().getClassLoader().getResource( "vnfd1.json" ),
                VnfDescriptor.class );
        }
        catch( Exception ex )
        {
            ex.printStackTrace();
        }


        return vnfDescriptor;
    }

    public static AccountingRequest creatingAccountingRequestForVnf(
        NetworkService networkService,
        NetworkServiceInstantiateReply reply,
        NetworkServiceActivationRequest request,
        VnfDescriptor vnfDescriptor,
        String vnfInstanceId
    )
    {
        logger.info( "Creating an accounting request for vnf with id={}", vnfDescriptor.getId() );
        ObjectMapper mapper = new ObjectMapper();
        AccountingRequest accountingRequest = new AccountingRequest();
        accountingRequest.setInstanceId( vnfInstanceId );
        accountingRequest.setAgreementId( "vnf" + vnfInstanceId );
        accountingRequest.setProductId( vnfDescriptor.getId().toString() );
        accountingRequest.setProviderId( vnfDescriptor.getProviderId().toString() );
        accountingRequest.setClientId( networkService.getNsd().getProviderId() );
        accountingRequest.setStatus( "running" );
        accountingRequest.setBillingModel( vnfDescriptor.getBillingModel().getModel() );
        accountingRequest.setPeriodCost( vnfDescriptor.getBillingModel().getPrice().getMaxPerPeriod() );
        accountingRequest.setSetupCost( vnfDescriptor.getBillingModel().getPrice().getSetup() );
        accountingRequest.setPriceUnit( vnfDescriptor.getBillingModel().getPrice().getUnit() );
        accountingRequest.setProductType( "vnf" );
        accountingRequest.setPeriod( "P1M" );
        accountingRequest.setRelatives( networkService.getNsd().getId() );
        accountingRequest.setRenew( true );

        for( Sla sla : networkService.getNsd().getSla() )
        {
            if( sla.getId().equalsIgnoreCase( request.getFlavorId() ) )
            {
                for( ConstituentVnfSla constituentVnfSla : sla.getConstituentVnf() )
                {
                    if( constituentVnfSla.getVnfReference().equalsIgnoreCase( vnfDescriptor.getId().toString() ) )
                    {
                        accountingRequest.setFlavour( constituentVnfSla.getVnfFlavourIdReference() );
                    }
                }
            }
        }

        try
        {
            logger.info( "Printing accounting request for vnf instance id={} ", vnfInstanceId );
            logger.info( mapper.writerWithDefaultPrettyPrinter().writeValueAsString( accountingRequest ) );
        }
        catch( Exception ex)
        {
            ex.printStackTrace();
        }

        return accountingRequest;
    }

    public static AccountingRequest createAccountingRequestForNetworkService( 
        NetworkService service, NetworkServiceInstantiateReply reply, NetworkServiceActivationRequest request )
    {
        logger.info( "Creating an accounting request for NetworkService={} and NsInstance={}",
            service.getNsd().getId(), reply.getId() );


        AccountingRequest accountingRequest = new AccountingRequest();
        for( Sla sla : service.getNsd().getSla() )
        {
            if ( sla.getId().equalsIgnoreCase( request.getFlavorId() ) )
            {
                accountingRequest.setBillingModel( sla.getBilling().getModel() );
                accountingRequest.setPeriodCost( sla.getBilling().getPrice().getPricePerPeriod() );
                accountingRequest.setPriceUnit( sla.getBilling().getPrice().getUnit() );
                accountingRequest.setSetupCost( sla.getBilling().getPrice().getSetup() );
                accountingRequest.setFlavour( sla.getSlaKey() );
            }
        }
        accountingRequest.setProviderId(  service.getNsd().getProviderId() );
        accountingRequest.setInstanceId( reply.getId() );
        accountingRequest.setProductId( service.getNsd().getId() );
        accountingRequest.setAgreementId( "ns" + reply.getId() );
        accountingRequest.setProductType( "ns" );
        accountingRequest.setClientId( request.getCustomerId() );
        if ( reply.getStatus().equalsIgnoreCase( "INSTANTIATED" ) )
        {
            accountingRequest.setStatus( "running" );
        }
        else
        {
            accountingRequest.setStatus( reply.getStatus() );
        }
        accountingRequest.setPeriod( "P1D" );
        accountingRequest.setRenew( true );

        List<String> relatives = new ArrayList<>();
        for( Vnfr vnfr : reply.getVnfrs() )
        {
            relatives.add( vnfr.getVnfdId() );
        }
        accountingRequest.setRelatives( relatives.toString().replace( "[", "" ).replace( "]", "" )  );

        logger.info( "Accounting Request for network service was created" );
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValueAsString( accountingRequest );
        } catch( Exception ex )
        {
            ex.printStackTrace();
        }

        return accountingRequest;
    }
}
