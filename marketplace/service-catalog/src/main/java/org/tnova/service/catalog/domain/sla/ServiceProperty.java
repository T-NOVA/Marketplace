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
import java.util.HashMap;
import java.util.Map;

@JsonInclude( JsonInclude.Include.NON_NULL )
@Generated( "org.jsonschema2pojo" )
@JsonPropertyOrder( { "name", "serviceName", "variableSet" } )
public class ServiceProperty
{

    @JsonProperty( "name" )
    private String name;

    @JsonProperty( "serviceName" )
    private String serviceName;

    @JsonProperty( "variableSet" )
    private VariableSet variableSet;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public ServiceProperty()
    {
    }

    /**
     * @param variableSet
     * @param name
     * @param serviceName
     */
    public ServiceProperty( String name, String serviceName, VariableSet variableSet )
    {
        this.name = name;
        this.serviceName = serviceName;
        this.variableSet = variableSet;
    }

    /**
     * @return The name
     */
    @JsonProperty( "name" )
    public String getName()
    {
        return name;
    }

    /**
     * @param name The name
     */
    @JsonProperty( "name" )
    public void setName( String name )
    {
        this.name = name;
    }

    public ServiceProperty withName( String name )
    {
        this.name = name;
        return this;
    }

    /**
     * @return The serviceName
     */
    @JsonProperty( "serviceName" )
    public String getServiceName()
    {
        return serviceName;
    }

    /**
     * @param serviceName The serviceName
     */
    @JsonProperty( "serviceName" )
    public void setServiceName( String serviceName )
    {
        this.serviceName = serviceName;
    }

    public ServiceProperty withServiceName( String serviceName )
    {
        this.serviceName = serviceName;
        return this;
    }

    /**
     * @return The variableSet
     */
    @JsonProperty( "variableSet" )
    public VariableSet getVariableSet()
    {
        return variableSet;
    }

    /**
     * @param variableSet The variableSet
     */
    @JsonProperty( "variableSet" )
    public void setVariableSet( VariableSet variableSet )
    {
        this.variableSet = variableSet;
    }

    public ServiceProperty withVariableSet( VariableSet variableSet )
    {
        this.variableSet = variableSet;
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

    public ServiceProperty withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

}
