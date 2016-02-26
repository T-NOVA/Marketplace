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

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude( JsonInclude.Include.NON_NULL )
@Generated( "org.jsonschema2pojo" )
@JsonPropertyOrder( { "name", "requirements", "serviceName" } )
public class ServiceDescriptionTerm
{

    @JsonProperty( "name" )
    private String name;

    @JsonProperty( "requirements" )
    private List<Requirement> requirements = new ArrayList<Requirement>();

    @JsonProperty( "serviceName" )
    private String serviceName;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public ServiceDescriptionTerm()
    {
    }

    public ServiceDescriptionTerm( String name, List<Requirement> requirements, String serviceName )
    {
        this.name = name;
        this.requirements = requirements;
        this.serviceName = serviceName;
    }

    @JsonProperty( "name" )
    public String getName()
    {
        return name;
    }

    @JsonProperty( "name" )
    public void setName( String name )
    {
        this.name = name;
    }

    public ServiceDescriptionTerm withName( String name )
    {
        this.name = name;
        return this;
    }

    @JsonProperty( "requirements" )
    public List<Requirement> getRequirements()
    {
        return requirements;
    }

    @JsonProperty( "requirements" )
    public void setRequirements( List<Requirement> requirements )
    {
        this.requirements = requirements;
    }

    public ServiceDescriptionTerm withRequirements( List<Requirement> requirements )
    {
        this.requirements = requirements;
        return this;
    }

    @JsonProperty( "serviceName" )
    public String getServiceName()
    {
        return serviceName;
    }

    @JsonProperty( "serviceName" )
    public void setServiceName( String serviceName )
    {
        this.serviceName = serviceName;
    }

    public ServiceDescriptionTerm withServiceName( String serviceName )
    {
        this.serviceName = serviceName;
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

    public ServiceDescriptionTerm withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

}
