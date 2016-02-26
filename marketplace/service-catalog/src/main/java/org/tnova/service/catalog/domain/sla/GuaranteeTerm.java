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

//@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonPropertyOrder( { "businessValueList", "name", "qualifyingCondition", "serviceLevelObjetive", "serviceScope" } )
public class GuaranteeTerm
{

    @JsonProperty( "businessValueList" )
    private BusinessValueList businessValueList;
    @JsonProperty( "name" )
    private String name;
    @JsonProperty( "qualifyingCondition" )
    private Object qualifyingCondition;
    @JsonProperty( "serviceLevelObjetive" )
    private ServiceLevelObjetive serviceLevelObjetive;
    @JsonProperty( "serviceScope" )
    private Object serviceScope;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public GuaranteeTerm()
    {
    }

    public GuaranteeTerm( BusinessValueList businessValueList, String name, Object qualifyingCondition,
        ServiceLevelObjetive serviceLevelObjetive, Object serviceScope )
    {
        this.businessValueList = businessValueList;
        this.name = name;
        this.qualifyingCondition = qualifyingCondition;
        this.serviceLevelObjetive = serviceLevelObjetive;
        this.serviceScope = serviceScope;
    }

    @JsonProperty( "businessValueList" )
    public BusinessValueList getBusinessValueList()
    {
        return businessValueList;
    }

    @JsonProperty( "businessValueList" )
    public void setBusinessValueList( BusinessValueList businessValueList )
    {
        this.businessValueList = businessValueList;
    }

    public GuaranteeTerm withBusinessValueList( BusinessValueList businessValueList )
    {
        this.businessValueList = businessValueList;
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

    public GuaranteeTerm withName( String name )
    {
        this.name = name;
        return this;
    }

    @JsonProperty( "qualifyingCondition" )
    public Object getQualifyingCondition()
    {
        return qualifyingCondition;
    }

    @JsonProperty( "qualifyingCondition" )
    public void setQualifyingCondition( Object qualifyingCondition )
    {
        this.qualifyingCondition = qualifyingCondition;
    }

    public GuaranteeTerm withQualifyingCondition( Object qualifyingCondition )
    {
        this.qualifyingCondition = qualifyingCondition;
        return this;
    }

    @JsonProperty( "serviceLevelObjetive" )
    public ServiceLevelObjetive getServiceLevelObjetive()
    {
        return serviceLevelObjetive;
    }

    @JsonProperty( "serviceLevelObjetive" )
    public void setServiceLevelObjetive( ServiceLevelObjetive serviceLevelObjetive )
    {
        this.serviceLevelObjetive = serviceLevelObjetive;
    }

    public GuaranteeTerm withServiceLevelObjetive( ServiceLevelObjetive serviceLevelObjetive )
    {
        this.serviceLevelObjetive = serviceLevelObjetive;
        return this;
    }

    @JsonProperty( "serviceScope" )
    public Object getServiceScope()
    {
        return serviceScope;
    }

    @JsonProperty( "serviceScope" )
    public void setServiceScope( Object serviceScope )
    {
        this.serviceScope = serviceScope;
    }

    public GuaranteeTerm withServiceScope( Object serviceScope )
    {
        this.serviceScope = serviceScope;
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

    public GuaranteeTerm withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

}
