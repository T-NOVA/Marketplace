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
@JsonPropertyOrder( { "context", "name", "templateId", "terms" } )
public class SlaTemplate
{
    @JsonProperty( "context" )
    private Context context;
    @JsonProperty( "name" )
    private String name;

    @JsonProperty( "templateId" )
    private String templateId;

    @JsonProperty( "terms" )
    private Terms terms;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public SlaTemplate()
    {
    }

    public SlaTemplate( Context context, String name, String templateId, Terms terms )
    {
        this.context = context;
        this.name = name;
        this.templateId = templateId;
        this.terms = terms;
    }

    @JsonProperty( "context" )
    public Context getContext()
    {
        return context;
    }

    @JsonProperty( "context" )
    public void setContext( Context context )
    {
        this.context = context;
    }

    public SlaTemplate withContext( Context context )
    {
        this.context = context;
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

    public SlaTemplate withName( String name )
    {
        this.name = name;
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

    public SlaTemplate withTemplateId( String templateId )
    {
        this.templateId = templateId;
        return this;
    }

    @JsonProperty( "terms" )
    public Terms getTerms()
    {
        return terms;
    }

    @JsonProperty( "terms" )
    public void setTerms( Terms terms )
    {
        this.terms = terms;
    }

    public SlaTemplate withTerms( Terms terms )
    {
        this.terms = terms;
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

    public SlaTemplate withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

}
