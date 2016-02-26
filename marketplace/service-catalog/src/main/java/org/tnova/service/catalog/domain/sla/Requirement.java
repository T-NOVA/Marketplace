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
@JsonPropertyOrder( { "name", "unit", "value" } )
public class Requirement
{

    @JsonProperty( "name" )
    private String name;

    @JsonProperty( "unit" )
    private String unit;

    @JsonProperty( "value" )
    private String value;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Requirement() {}

    public Requirement( String name, String unit, String value )
    {
        this.name = name;
        this.unit = unit;
        this.value = value;
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

    public Requirement withName( String name )
    {
        this.name = name;
        return this;
    }

    @JsonProperty( "unit" )
    public String getUnit()
    {
        return unit;
    }

    @JsonProperty( "unit" )
    public void setUnit( String unit )
    {
        this.unit = unit;
    }

    public Requirement withUnit( String unit )
    {
        this.unit = unit;
        return this;
    }

    @JsonProperty( "value" )
    public String getValue()
    {
        return value;
    }

    @JsonProperty( "value" )
    public void setValue( String value )
    {
        this.value = value;
    }

    public Requirement withValue( String value )
    {
        this.value = value;
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

    public Requirement withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

}
