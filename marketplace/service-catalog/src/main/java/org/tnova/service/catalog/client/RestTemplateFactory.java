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

package org.tnova.service.catalog.client;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateFactory implements FactoryBean<RestTemplate>, InitializingBean
{

    private RestTemplate restTemplate;

    public RestTemplateFactory()
    {
        super();
    }

    @Override
    public RestTemplate getObject() throws Exception
    {
        return null;
    }

    @Override
    public Class<?> getObjectType()
    {
        return RestTemplate.class;
    }

    @Override
    public boolean isSingleton()
    {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        final int timeout = 5;

        final RequestConfig requestConfig
            = RequestConfig.custom().setConnectTimeout( timeout * 1000  ).setSocketTimeout( timeout * 1000 ).build();

        final BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
            new AuthScope( "localhost", 8080, AuthScope.ANY_REALM ),
            new UsernamePasswordCredentials( "user1", "user1Pass" ) );
            final CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig( requestConfig )
                .setDefaultCredentialsProvider( credentialsProvider )
                .build();

        final ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory( client );
        restTemplate = new RestTemplate( requestFactory );


    }
}
