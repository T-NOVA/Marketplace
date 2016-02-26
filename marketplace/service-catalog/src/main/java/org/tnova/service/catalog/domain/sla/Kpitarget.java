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
@JsonPropertyOrder( { "customServiceLevel", "kpiName" } )
public class Kpitarget
{

    @JsonProperty( "customServiceLevel" )
    private String customServiceLevel;

    @JsonProperty( "kpiName" )
    private String kpiName;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Kpitarget()
    {
    }

    public Kpitarget( String customServiceLevel, String kpiName )
    {
        this.customServiceLevel = customServiceLevel;
        this.kpiName = kpiName;
    }

    @JsonProperty( "customServiceLevel" )
    public String getCustomServiceLevel()
    {
        return customServiceLevel;
    }

    @JsonProperty( "customServiceLevel" )
    public void setCustomServiceLevel( String customServiceLevel )
    {
        this.customServiceLevel = customServiceLevel;
    }

    public Kpitarget withCustomServiceLevel( String customServiceLevel )
    {
        this.customServiceLevel = customServiceLevel;
        return this;
    }

    @JsonProperty( "kpiName" )
    public String getKpiName()
    {
        return kpiName;
    }

    @JsonProperty( "kpiName" )
    public void setKpiName( String kpiName )
    {
        this.kpiName = kpiName;
    }

    public Kpitarget withKpiName( String kpiName )
    {
        this.kpiName = kpiName;
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

    public Kpitarget withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

}
