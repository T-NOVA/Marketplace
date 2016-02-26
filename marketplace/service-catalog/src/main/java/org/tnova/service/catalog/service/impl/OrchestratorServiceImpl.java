/*
 * Copyright 2016  CloudStreet Oy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tnova.service.catalog.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tnova.service.catalog.domain.nsd.NetworkService;
import org.tnova.service.catalog.service.OrchestratorService;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrchestratorServiceImpl implements OrchestratorService
{
    private static final Logger logger = LoggerFactory.getLogger( NsdServiceImpl.class );

    private RestTemplate restTemplate;

    @Value( "${tnova.orchestrator.host}" )
    private String orchestratorUri;

    @Value( "${tnova.orchestrator.feature.status}" )
    private String status;

    @Autowired
    public OrchestratorServiceImpl( RestTemplate restTemplate )
    {
        this.restTemplate = restTemplate;
    }

    public boolean createNsdToOrchestrator( NetworkService networkService )
    {
        restTemplate = new RestTemplate();
        logger.info( "Publishing a network service to orchestrator {}", orchestratorUri );

        if( status.equalsIgnoreCase( "disabled" ) )
        {
            logger.info( "Publishing to orchestrator is disabled. Print the nsd json to log file" );
            ObjectMapper mapper = new ObjectMapper();
            try
            {
                logger.info( mapper.writerWithDefaultPrettyPrinter().writeValueAsString( networkService ) );

                return true;
            }
            catch( Exception ex )
            {
                ex.printStackTrace();
            }

        }

        logger.info( "Publishing network service to orchestrator" );
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            String networkServiceRequest = mapper.writeValueAsString( networkService );

            System.out.println( mapper.writerWithDefaultPrettyPrinter().writeValueAsString( networkService ));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType( MediaType.APPLICATION_JSON );

            HttpEntity<String> entity = new HttpEntity<>( networkServiceRequest, headers );

            final ResponseEntity<String> responseEntity = restTemplate
                .exchange( orchestratorUri, HttpMethod.POST, entity, String.class );

            if( responseEntity != null )
            {
                logger.info( "Receiving response from orchestrator with status = {} and description = {}",
                    responseEntity.getStatusCode(), responseEntity.getStatusCode().getReasonPhrase() );
                logger.info( "Receiving response from orchestrator {}", responseEntity.getBody() );

                if( responseEntity.getStatusCode() != HttpStatus.OK )
                {
                    return false;
                }

                return true;
            }

        }
        catch( Exception ex )
        {
            ex.printStackTrace();
        }

        logger.info( "Response is null. An error occurred  during communication with orchestrator. Please check" );
        return false;
    }

    public boolean modifyNsdToOrchestrator( NetworkService networkService )
    {
        logger.info( "Updating a network service with id={}", networkService.getNsd().getId() );

        RestTemplate restTemplate = new RestTemplate();

        if ( status.equalsIgnoreCase( "disabled" ) )
        {
            try
            {
                ObjectMapper objectMapper = new ObjectMapper();
                logger.info( objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString( networkService ) );

                return true;

            }
            catch( Exception ex )
            {
                logger.error( "An exception occred during serialization, please check !!!" );
            }

            return false;
        }

        try
        {
            ObjectMapper mapper = new ObjectMapper();
            String networkServiceRequest = mapper.writeValueAsString( networkService );
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType( MediaType.APPLICATION_JSON );
            HttpEntity<String> entity = new HttpEntity<>( networkServiceRequest, headers );

            Map<String, String> modifyParams = new HashMap<>();
            modifyParams.put( "id", networkService.getNsd().getId() );


            final ResponseEntity<String> responseEntity
                = restTemplate.exchange( orchestratorUri + "/{id}", HttpMethod.PUT, entity, String.class, modifyParams );

            if ( responseEntity.getStatusCode() != HttpStatus.OK )
            {
                logger.error( "An error occured during updating network service with id={} to orchestrator. "
                    + "Response code from orchestrator is=", responseEntity.getStatusCode().getReasonPhrase() );

                return false;
            }
            else
            {
                logger.info( "Successful modification of network service with id={} to orchestrator" );

                return true;
            }

        }
        catch( Exception ex )
        {
            logger.error( "An exception occured during modification of Network Service with id={} in orchestrator with message {}",
                networkService.getNsd().getId(), ex.getMessage() );

            return false;

        }
    }

    public boolean deleteNsdToOrchestrator( NetworkService networkService )
    {
        logger.info( "Deleting a network service with id={}.", networkService.getNsd().getId() );

        if( status.equalsIgnoreCase( "disabled" ) )
        {
            logger.info( "Publishing to orchestrator is disabled. Print the nsd json to log file" );
            ObjectMapper mapper = new ObjectMapper();
            try
            {
                logger.info( mapper.writerWithDefaultPrettyPrinter().writeValueAsString( networkService ) );

                return true;
            }
            catch( Exception ex )
            {
                ex.printStackTrace();
            }
        }
        else
        {
            try
            {
                logger.info( "Deleting a network service with id={} from orchestrator.", networkService.getNsd().getId() );
//                HttpHeaders headers = new HttpHeaders();
//                headers.setContentType( MediaType.APPLICATION_JSON );
                RestTemplate restTemplate = new RestTemplate();

                Map<String, String> deleteParams = new HashMap<>();
                deleteParams.put( "id", networkService.getNsd().getId() );
                restTemplate.delete( orchestratorUri+"/{id}", deleteParams  );

                return true;

            }
            catch( Exception ex )
            {
                ex.printStackTrace();
            }
        }

        return false;
    }
}
