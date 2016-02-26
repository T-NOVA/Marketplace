package org.tnova.service.selection.service.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tnova.service.selection.domain.vnf.VnfDescriptor;
import org.tnova.service.selection.service.VnfService;
import org.tnova.service.selection.util.Helpers;

import java.util.HashMap;

@Service
public class VnfServiceImpl implements VnfService
{
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger( VnfServiceImpl.class );

    @Value( "${tnova.vnf.feature.status}" )
    private String isVnfStoreEnabled;

    @Value( "${tnova.vnf.module.host}" )
    private String vnfStoreUrl;

    @Override
    public VnfDescriptor getVnfById( String vnfdId )
    {
        VnfDescriptor vnfDescriptor = new VnfDescriptor();
        RestTemplate restTemplate = new RestTemplate();

        if ( isVnfStoreEnabled.equalsIgnoreCase( "disabled" ) )
        {
            logger.info( "Online VNF retrieval is disabled. Retrieving VNF from a file, " );
            Helpers helpers = new Helpers();
            vnfDescriptor = helpers.createMockVnf();
        }
        else
        {
            logger.info( "retrieving vnf with id={} from host={}", vnfdId, vnfStoreUrl );
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType( MediaType.APPLICATION_JSON );

            HashMap<String, String> params = new HashMap<>();
            params.put( "id", vnfdId );

            ResponseEntity<VnfDescriptor> response = restTemplate.getForEntity( vnfStoreUrl + "/{id}/",
                VnfDescriptor.class, params );

            if ( response.getStatusCode() != HttpStatus.OK )
            {
                logger.error( "An error occured during retrieval of VNF, with error code = {} and body {}",
                    response.getStatusCode().getReasonPhrase(), response.getBody().toString() );

                return null;
            }

            vnfDescriptor = response.getBody();
        }

        return vnfDescriptor;
    }
}
