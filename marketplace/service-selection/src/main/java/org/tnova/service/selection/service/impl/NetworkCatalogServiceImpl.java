package org.tnova.service.selection.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tnova.service.selection.domain.nsd.NetworkService;
import org.tnova.service.selection.service.NetworkCatalogService;

import java.io.IOException;
import java.util.HashMap;

@Service
public class NetworkCatalogServiceImpl implements NetworkCatalogService
{
    private final Logger logger = LoggerFactory.getLogger( NetworkCatalogServiceImpl.class );

    @Value("${tnova.service.catalog.host}")
    private String businessServiceCatalogUrl;

    @Override
    public NetworkService getNetworkServiceByNsdId( String id )
    {
        logger.info( "Retrieving network service with id = {} from business service catalog", id );

        RestTemplate restTemplate = new RestTemplate();
        final HashMap<String, String> urlVariables = new HashMap<String, String>();
        urlVariables.put("id", id );
        NetworkService networkService = null;
        ResponseEntity<String> responseEntity = null;

        try
        {
            responseEntity = restTemplate
                .getForEntity( businessServiceCatalogUrl + "/{id}", String.class, urlVariables );
        }
        catch( Exception ex )
        {
            //do nothing
        }


        if ( responseEntity == null || responseEntity.getStatusCode() == HttpStatus.NOT_FOUND )
        {
            logger.warn( "Unable to find network service with specified id = {}. Return null",  id );

            return networkService;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
             networkService = mapper.readValue( responseEntity.getBody(), NetworkService.class );
        }
        catch(  IOException ex )
        {
            ex.printStackTrace();
        }


        return networkService;
    }
}
