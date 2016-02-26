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

package org.tnova.service.selection.domain;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonPropertyOrder(
    {
        "id",
        "nsd_id",
        "status",
        "created_at",
        "updated_at",
        "vnfs"
    }
)
public class NetworkServiceActivationReply
{
    @JsonProperty( "id" )
    private Integer id;
    @JsonProperty( "nsd_id" )
    private String nsdId;
    @JsonProperty( "status" )
    private String status;
    @JsonProperty( "created_at" )
    private String createdAt;
    @JsonProperty( "updated_at" )
    private String updatedAt;
    @JsonProperty( "vnfs" )
    private List<Vnf> vnfs = new ArrayList<Vnf>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public NetworkServiceActivationReply() {}

    public NetworkServiceActivationReply( Integer id, String nsdId, String status, String createdAt, String updatedAt,
        List<Vnf> vnfs )
    {
        this.id = id;
        this.nsdId = nsdId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.vnfs = vnfs;
    }

    @JsonProperty( "id" )
    public Integer getId()
    {
        return id;
    }

    @JsonProperty( "id" )
    public void setId( Integer id )
    {
        this.id = id;
    }

    public NetworkServiceActivationReply withId( Integer id )
    {
        this.id = id;
        return this;
    }

    @JsonProperty( "nsd_id" )
    public String getNsdId()
    {
        return nsdId;
    }

    @JsonProperty( "nsd_id" )
    public void setNsdId( String nsdId )
    {
        this.nsdId = nsdId;
    }

    public NetworkServiceActivationReply withNsdId( String nsdId )
    {
        this.nsdId = nsdId;
        return this;
    }

    @JsonProperty( "status" )
    public String getStatus()
    {
        return status;
    }

    @JsonProperty( "status" )
    public void setStatus( String status )
    {
        this.status = status;
    }

    public NetworkServiceActivationReply withStatus( String status )
    {
        this.status = status;
        return this;
    }

    @JsonProperty( "created_at" )
    public String getCreatedAt()
    {
        return createdAt;
    }

    @JsonProperty( "created_at" )
    public void setCreatedAt( String createdAt )
    {
        this.createdAt = createdAt;
    }

    public NetworkServiceActivationReply withCreatedAt( String createdAt )
    {
        this.createdAt = createdAt;
        return this;
    }

    @JsonProperty( "updated_at" )
    public String getUpdatedAt()
    {
        return updatedAt;
    }

    @JsonProperty( "updated_at" )
    public void setUpdatedAt( String updatedAt )
    {
        this.updatedAt = updatedAt;
    }

    public NetworkServiceActivationReply withUpdatedAt( String updatedAt )
    {
        this.updatedAt = updatedAt;
        return this;
    }

    @JsonProperty( "vnfs" )
    public List<Vnf> getVnfs()
    {
        return vnfs;
    }

    @JsonProperty( "vnfs" )
    public void setVnfs( List<Vnf> vnfs )
    {
        this.vnfs = vnfs;
    }

    public NetworkServiceActivationReply withVnfs( List<Vnf> vnfs )
    {
        this.vnfs = vnfs;
        return this;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString( this );
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

    public NetworkServiceActivationReply withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( id ).append( nsdId ).append( status ).append( createdAt )
            .append( updatedAt ).append( vnfs ).append( additionalProperties ).toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof NetworkServiceActivationReply ) == false )
        {
            return false;
        }
        NetworkServiceActivationReply rhs = ( (NetworkServiceActivationReply) other );

        return new EqualsBuilder().append( id, rhs.id ).append( nsdId, rhs.nsdId ).append( status, rhs.status )
            .append( createdAt, rhs.createdAt ).append( updatedAt, rhs.updatedAt ).append( vnfs, rhs.vnfs )
            .append( additionalProperties, rhs.additionalProperties ).isEquals();
    }

}
