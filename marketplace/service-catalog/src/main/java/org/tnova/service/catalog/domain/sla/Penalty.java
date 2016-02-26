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
@JsonPropertyOrder( { "type", "unit", "validity", "value" } )
public class Penalty
{
    @JsonProperty( "type" )
    private String type;

    @JsonProperty( "unit" )
    private String unit;

    @JsonProperty( "validity" )
    private String validity;

    @JsonProperty( "expression" )
    private int expression;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Penalty() {}

    public Penalty( String type, String unit, String validity, int expression )
    {
        this.type = type;
        this.unit = unit;
        this.validity = validity;
        this.expression = expression;
    }

    @JsonProperty( "type" )
    public String getType()
    {
        return type;
    }

    @JsonProperty( "type" )
    public void setType( String type )
    {
        this.type = type;
    }

    public Penalty withType( String type )
    {
        this.type = type;
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

    public Penalty withUnit( String unit )
    {
        this.unit = unit;
        return this;
    }

    @JsonProperty( "validity" )
    public String getValidity()
    {
        return validity;
    }

    @JsonProperty( "validity" )
    public void setValidity( String validity )
    {
        this.validity = validity;
    }

    public Penalty withValidity( String validity )
    {
        this.validity = validity;
        return this;
    }

    @JsonProperty( "expression" )
    public int getValue()
    {
        return expression;
    }

    @JsonProperty( "expression" )
    public void setValue( int value )
    {
        this.expression = value;
    }

    public Penalty withValue( int expression )
    {
        this.expression = expression;
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

    public Penalty withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

}
