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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonPropertyOrder( { "guaranteeTerms", "serviceDescriptionTerm", "serviceProperties" } )
public class AllTerms
{

    @JsonProperty( "guaranteeTerms" )
    private List<GuaranteeTerm> guaranteeTerms = new ArrayList<GuaranteeTerm>();

    @JsonProperty( "serviceDescriptionTerm" )
    private ServiceDescriptionTerm serviceDescriptionTerm;

    @JsonProperty( "serviceProperties" )
    private List<ServiceProperty> serviceProperties = new ArrayList<ServiceProperty>();

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public AllTerms() {}

    public AllTerms( List<GuaranteeTerm> guaranteeTerms, ServiceDescriptionTerm serviceDescriptionTerm,
        List<ServiceProperty> serviceProperties )
    {
        this.guaranteeTerms = guaranteeTerms;
        this.serviceDescriptionTerm = serviceDescriptionTerm;
        this.serviceProperties = serviceProperties;
    }

    @JsonProperty( "guaranteeTerms" )
    public List<GuaranteeTerm> getGuaranteeTerms()
    {
        return guaranteeTerms;
    }

    @JsonProperty( "guaranteeTerms" )
    public void setGuaranteeTerms( List<GuaranteeTerm> guaranteeTerms )
    {
        this.guaranteeTerms = guaranteeTerms;
    }

    public AllTerms withGuaranteeTerms( List<GuaranteeTerm> guaranteeTerms )
    {
        this.guaranteeTerms = guaranteeTerms;
        return this;
    }

    @JsonProperty( "serviceDescriptionTerm" )
    public ServiceDescriptionTerm getServiceDescriptionTerm()
    {
        return serviceDescriptionTerm;
    }

    @JsonProperty( "serviceDescriptionTerm" )
    public void setServiceDescriptionTerm( ServiceDescriptionTerm serviceDescriptionTerm )
    {
        this.serviceDescriptionTerm = serviceDescriptionTerm;
    }

    public AllTerms withServiceDescriptionTerm( ServiceDescriptionTerm serviceDescriptionTerm )
    {
        this.serviceDescriptionTerm = serviceDescriptionTerm;
        return this;
    }

    @JsonProperty( "serviceProperties" )
    public List<ServiceProperty> getServiceProperties()
    {
        return serviceProperties;
    }

    @JsonProperty( "serviceProperties" )
    public void setServiceProperties( List<ServiceProperty> serviceProperties )
    {
        this.serviceProperties = serviceProperties;
    }

    public AllTerms withServiceProperties( List<ServiceProperty> serviceProperties )
    {
        this.serviceProperties = serviceProperties;
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

    public AllTerms withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

}
