package org.tnova.service.selection.domain.instantiate;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonPropertyOrder( {
    "vnfd_id",
    //"vnfi_id",
    "vnfr_id",
    "pop_id",
    "status",
    "vnf_addresses" } )
public class Vnfr
{

    @JsonProperty( "vnfd_id" )
    private String vnfdId;
//    @JsonProperty( "vnfi_id" )
//    private List<String> vnfiId = new ArrayList<String>();
//    @JsonProperty( "vnfr_id" )
    private String vnfrId;
    @JsonProperty( "pop_id" )
    private String popId;
    @JsonProperty( "status" )
    private String status;
    @JsonProperty( "vnf_addresses" )
    private VnfAddresses vnfAddresses;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Vnfr()
    {
    }

    public Vnfr( String vnfdId, String vnfrId, String popId, String status,
        VnfAddresses vnfAddresses )
    {
        this.vnfdId = vnfdId;
//        this.vnfiId = vnfiId;
        this.vnfrId = vnfrId;
        this.popId = popId;
        this.status = status;
        this.vnfAddresses = vnfAddresses;
    }

    /**
     * @return The vnfdId
     */
    @JsonProperty( "vnfd_id" )
    public String getVnfdId()
    {
        return vnfdId;
    }

    /**
     * @param vnfdId The vnfd_id
     */
    @JsonProperty( "vnfd_id" )
    public void setVnfdId( String vnfdId )
    {
        this.vnfdId = vnfdId;
    }

    public Vnfr withVnfdId( String vnfdId )
    {
        this.vnfdId = vnfdId;
        return this;
    }

//    /**
//     * @return The vnfiId
//     */
//    @JsonProperty( "vnfi_id" )
//    public List<String> getVnfiId()
//    {
//        return vnfiId;
//    }
//
//    /**
//     * @param vnfiId The vnfi_id
//     */
//    @JsonProperty( "vnfi_id" )
//    public void setVnfiId( List<String> vnfiId )
//    {
//        this.vnfiId = vnfiId;
//    }
//
//    public Vnfr withVnfiId( List<String> vnfiId )
//    {
//        this.vnfiId = vnfiId;
//        return this;
//    }

    /**
     * @return The vnfrId
     */
    @JsonProperty( "vnfr_id" )
    public String getVnfrId()
    {
        return vnfrId;
    }

    /**
     * @param vnfrId The vnfr_id
     */
    @JsonProperty( "vnfr_id" )
    public void setVnfrId( String vnfrId )
    {
        this.vnfrId = vnfrId;
    }

    public Vnfr withVnfrId( String vnfrId )
    {
        this.vnfrId = vnfrId;
        return this;
    }

    /**
     * @return The popId
     */
    @JsonProperty( "pop_id" )
    public String getPopId()
    {
        return popId;
    }

    /**
     * @param popId The pop_id
     */
    @JsonProperty( "pop_id" )
    public void setPopId( String popId )
    {
        this.popId = popId;
    }

    public Vnfr withPopId( String popId )
    {
        this.popId = popId;
        return this;
    }

    /**
     * @return The status
     */
    @JsonProperty( "status" )
    public String getStatus()
    {
        return status;
    }

    /**
     * @param status The status
     */
    @JsonProperty( "status" )
    public void setStatus( String status )
    {
        this.status = status;
    }

    public Vnfr withStatus( String status )
    {
        this.status = status;
        return this;
    }

    /**
     * @return The vnfAddresses
     */
    @JsonProperty( "vnf_addresses" )
    public VnfAddresses getVnfAddresses()
    {
        return vnfAddresses;
    }

    /**
     * @param vnfAddresses The vnf_addresses
     */
    @JsonProperty( "vnf_addresses" )
    public void setVnfAddresses( VnfAddresses vnfAddresses )
    {
        this.vnfAddresses = vnfAddresses;
    }

    public Vnfr withVnfAddresses( VnfAddresses vnfAddresses )
    {
        this.vnfAddresses = vnfAddresses;
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

    public Vnfr withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( vnfdId ).append( vnfrId ).append( popId ).append( status )
            .append( vnfAddresses ).append( additionalProperties ).toHashCode();

//        .append( vnfiId ).
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof Vnfr ) == false )
        {
            return false;
        }
        Vnfr rhs = ( (Vnfr) other );
        return new EqualsBuilder().append( vnfdId, rhs.vnfdId )
            .append( vnfrId, rhs.vnfrId ).append( popId, rhs.popId ).append( status, rhs.status )
            .append( vnfAddresses, rhs.vnfAddresses ).append( additionalProperties, rhs.additionalProperties )
            .isEquals();

        //.append( vnfiId, rhs.vnfiId )
    }

}
