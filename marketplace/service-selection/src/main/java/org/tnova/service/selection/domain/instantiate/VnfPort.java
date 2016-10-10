package org.tnova.service.selection.domain.instantiate;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude( JsonInclude.Include.NON_NULL )
@Generated( "org.jsonschema2pojo" )
@JsonPropertyOrder( { "id", "vlink_ref", "physical_resource_id" } )
public class VnfPort
{

    @JsonProperty( "id" )
    private String id;
    @JsonProperty( "vlink_ref" )
    private String vlinkRef;
    @JsonProperty( "physical_resource_id" )
    private String physicalResourceId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public VnfPort()
    {
    }

    /**
     * @param id
     * @param vlinkRef
     * @param physicalResourceId
     */
    public VnfPort( String id, String vlinkRef, String physicalResourceId )
    {
        this.id = id;
        this.vlinkRef = vlinkRef;
        this.physicalResourceId = physicalResourceId;
    }

    /**
     * @return The id
     */
    @JsonProperty( "id" )
    public String getId()
    {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty( "id" )
    public void setId( String id )
    {
        this.id = id;
    }

    public VnfPort withId( String id )
    {
        this.id = id;
        return this;
    }

    /**
     * @return The vlinkRef
     */
    @JsonProperty( "vlink_ref" )
    public String getVlinkRef()
    {
        return vlinkRef;
    }

    /**
     * @param vlinkRef The vlink_ref
     */
    @JsonProperty( "vlink_ref" )
    public void setVlinkRef( String vlinkRef )
    {
        this.vlinkRef = vlinkRef;
    }

    public VnfPort withVlinkRef( String vlinkRef )
    {
        this.vlinkRef = vlinkRef;
        return this;
    }

    /**
     * @return The physicalResourceId
     */
    @JsonProperty( "physical_resource_id" )
    public String getPhysicalResourceId()
    {
        return physicalResourceId;
    }

    /**
     * @param physicalResourceId The physical_resource_id
     */
    @JsonProperty( "physical_resource_id" )
    public void setPhysicalResourceId( String physicalResourceId )
    {
        this.physicalResourceId = physicalResourceId;
    }

    public VnfPort withPhysicalResourceId( String physicalResourceId )
    {
        this.physicalResourceId = physicalResourceId;
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

    public VnfPort withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( id ).append( vlinkRef ).append( physicalResourceId )
            .append( additionalProperties ).toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof VnfPort ) == false )
        {
            return false;
        }
        VnfPort rhs = ( (VnfPort) other );
        return new EqualsBuilder().append( id, rhs.id ).append( vlinkRef, rhs.vlinkRef )
            .append( physicalResourceId, rhs.physicalResourceId )
            .append( additionalProperties, rhs.additionalProperties ).isEquals();
    }

}
