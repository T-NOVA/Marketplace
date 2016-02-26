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
@JsonPropertyOrder( { "kpitarget" } )
public class ServiceLevelObjetive
{

    @JsonProperty( "kpitarget" )
    private Kpitarget kpitarget;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public ServiceLevelObjetive()
    {
    }

    /**
     * @param kpitarget
     */
    public ServiceLevelObjetive( Kpitarget kpitarget )
    {
        this.kpitarget = kpitarget;
    }

    /**
     * @return The kpitarget
     */
    @JsonProperty( "kpitarget" )
    public Kpitarget getKpitarget()
    {
        return kpitarget;
    }

    /**
     * @param kpitarget The kpitarget
     */
    @JsonProperty( "kpitarget" )
    public void setKpitarget( Kpitarget kpitarget )
    {
        this.kpitarget = kpitarget;
    }

    public ServiceLevelObjetive withKpitarget( Kpitarget kpitarget )
    {
        this.kpitarget = kpitarget;
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

    public ServiceLevelObjetive withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

}
