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

@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonPropertyOrder( { "location", "metric", "name" } )
public class Variable
{

    @JsonProperty( "location" )
    private String location;

    @JsonProperty( "metric" )
    private String metric;

    @JsonProperty( "name" )
    private String name;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Variable()
    {
    }

    public Variable( String location, String metric, String name )
    {
        this.location = location;
        this.metric = metric;
        this.name = name;
    }

    @JsonProperty( "location" )
    public String getLocation()
    {
        return location;
    }

    @JsonProperty( "location" )
    public void setLocation( String location )
    {
        this.location = location;
    }

    public Variable withLocation( String location )
    {
        this.location = location;
        return this;
    }

    @JsonProperty( "metric" )
    public String getMetric()
    {
        return metric;
    }

    @JsonProperty( "metric" )
    public void setMetric( String metric )
    {
        this.metric = metric;
    }

    public Variable withMetric( String metric )
    {
        this.metric = metric;
        return this;
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

    public Variable withName( String name )
    {
        this.name = name;
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

    public Variable withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

}
