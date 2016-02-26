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

package org.tnova.service.catalog.domain.sla;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@JsonInclude( JsonInclude.Include. )
@JsonPropertyOrder( { "agreementInitiator", "agreementResponder", "service", "serviceProvider", "templateId" } )
public class Context
{

    @JsonProperty( "agreementInitiator" )
    private Object agreementInitiator;

    @JsonProperty( "agreementResponder" )
    private String agreementResponder;

    @JsonProperty( "service" )
    private String service;

    @JsonProperty( "serviceProvider" )
    private String serviceProvider;

    @JsonProperty( "templateId" )
    private String templateId;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Context() {}

    public Context( Object agreementInitiator, String agreementResponder, String service, String serviceProvider,
        String templateId )
    {
        this.agreementInitiator = agreementInitiator;
        this.agreementResponder = agreementResponder;
        this.service = service;
        this.serviceProvider = serviceProvider;
        this.templateId = templateId;
    }

    @JsonProperty( "agreementInitiator" )
    public Object getAgreementInitiator()
    {
        return agreementInitiator;
    }

    @JsonProperty( "agreementInitiator" )
    public void setAgreementInitiator( Object agreementInitiator )
    {
        this.agreementInitiator = agreementInitiator;
    }

    public Context withAgreementInitiator( Object agreementInitiator )
    {
        this.agreementInitiator = agreementInitiator;
        return this;
    }

    @JsonProperty( "agreementResponder" )
    public String getAgreementResponder()
    {
        return agreementResponder;
    }

    @JsonProperty( "agreementResponder" )
    public void setAgreementResponder( String agreementResponder )
    {
        this.agreementResponder = agreementResponder;
    }

    public Context withAgreementResponder( String agreementResponder )
    {
        this.agreementResponder = agreementResponder;
        return this;
    }

    @JsonProperty( "service" )
    public String getService()
    {
        return service;
    }

    @JsonProperty( "service" )
    public void setService( String service )
    {
        this.service = service;
    }

    public Context withService( String service )
    {
        this.service = service;
        return this;
    }

    @JsonProperty( "serviceProvider" )
    public String getServiceProvider()
    {
        return serviceProvider;
    }

    @JsonProperty( "serviceProvider" )
    public void setServiceProvider( String serviceProvider )
    {
        this.serviceProvider = serviceProvider;
    }

    public Context withServiceProvider( String serviceProvider )
    {
        this.serviceProvider = serviceProvider;
        return this;
    }

    @JsonProperty( "templateId" )
    public String getTemplateId()
    {
        return templateId;
    }

    @JsonProperty( "templateId" )
    public void setTemplateId( String templateId )
    {
        this.templateId = templateId;
    }

    public Context withTemplateId( String templateId )
    {
        this.templateId = templateId;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties()
    {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
    }

    public Context withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

}
