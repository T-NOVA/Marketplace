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

package org.tnova.service.selection;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

public class MyEmbeddedServletContainerCustomizer implements EmbeddedServletContainerCustomizer
{

    private static final Logger LOG = LoggerFactory.getLogger( MyEmbeddedServletContainerCustomizer.class );

    @Override
    public void customize( ConfigurableEmbeddedServletContainer factory )
    {
        if( factory instanceof TomcatEmbeddedServletContainerFactory )
        {
            customizeTomcat( (TomcatEmbeddedServletContainerFactory) factory );
        }
    }

    public void customizeTomcat( TomcatEmbeddedServletContainerFactory factory )
    {
        factory.addConnectorCustomizers( new TomcatConnectorCustomizer()
        {

            @Override
            public void customize( Connector connector )
            {
                connector.setAsyncTimeout( 300000 );
//                                Object defaultMaxThreads = connector.getAttribute("maxThreads");
//                                connector.setAttribute("maxThreads", MAX_THREADS);
//                                LOG.info("Changed Tomcat connector maxThreads from " + defaultMaxThreads + " to " + MAX_THREADS);
            }
        } );
    }
}
