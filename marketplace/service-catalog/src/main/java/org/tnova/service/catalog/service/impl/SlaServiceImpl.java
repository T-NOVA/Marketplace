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
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tnova.service.catalog.conversion.SlaTemplateGenerator;
import org.tnova.service.catalog.domain.nsd.Nsd;
import org.tnova.service.catalog.domain.sla.SlaTemplate;
import org.tnova.service.catalog.service.SlaService;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
public class SlaServiceImpl implements SlaService
{
    private static Logger logger = LoggerFactory.getLogger( SlaServiceImpl.class );

    @Value( "${tnova.sla.feature.status}" )
    private String activationStatus;

    @Value( "${tnova.sla.host}" )
    private String slaModuleUrl;

    @Value( "${tnova.sla.username}" )
    private String username;

    @Value( "${tnova.sla.password}" )
    private String password;

    @Override
    public boolean populateSlaTemplate( Nsd networkService ) throws Exception
    {
        logger.info( "Generate sla templates based on network service with id = {}", networkService.getId() );
        List<SlaTemplate> templates = SlaTemplateGenerator.convertNetorkServiceToSlaTemplate( networkService );

        if ( templates == null || templates.isEmpty() )
        {
            logger.warn( "No sla templates were generated. Please check possible errors!!!!" );

            return false;
        }

        for( SlaTemplate template : templates )
        {
            if ( activationStatus.equalsIgnoreCase( "enabled" ) )
            {
                logger.info( "Populating sla template with id = {} to sla module with url = {}",
                    template.getTemplateId(), slaModuleUrl );
                RestTemplate restTemplate = new RestTemplate();
                ObjectMapper mapper = new ObjectMapper(  );
                HttpEntity<String> request = new HttpEntity<String>(
                    mapper.writeValueAsString( template ),
                    createHeaders( username, password ) );

                ResponseEntity<String> response
                    = restTemplate.exchange(slaModuleUrl, HttpMethod.POST, request, String.class);

                if ( response == null || response.getStatusCode() != HttpStatus.CREATED )
                {
                    logger.error( "An error occurred during the request in sla module. PLEASE CHECK !!!" );
                    if ( response != null )
                    {
                        logger.error( "Error Code is: " + response.getStatusCode().getReasonPhrase() );
                        logger.error( "Body is: " + response.getBody() );

                    }
                    return  false;
                }
                else
                {
                    logger.info( "Request was successfully submitted to SLA module. " );
                }
            }
            else {
                logger.info( "Population to sla template is not active, printed in log for verification" );
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    logger.info( objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString( template ) );
                }
                catch( Exception ex )
                {
                    ex.printStackTrace();
                }
            }
        }

        return true;
    }

    private static HttpHeaders createHeaders( String username, String password ){

        HttpHeaders httpHeaders = new HttpHeaders();

        // Create authentication string
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64( auth.getBytes( Charset.forName( "US-ASCII" ) ) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set( "Authorization", authHeader );
        httpHeaders.setContentType( MediaType.APPLICATION_JSON );

        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add( MediaType.APPLICATION_JSON );
        httpHeaders.setAccept( mediaTypes );

        return httpHeaders;
    }
}
