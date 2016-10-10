package org.tnova.service.selection.domain.instantiate;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude( JsonInclude.Include.NON_NULL )
@Generated( "org.jsonschema2pojo" )
@JsonPropertyOrder( { "vnffg_id", "number_of_endpoints", "number_of_virtual_links", "dependent_virtual_links",
    "network_forwarding_path" } )
public class Vnffg
{

    @JsonProperty( "vnffg_id" )
    private String vnffgId;
    @JsonProperty( "number_of_endpoints" )
    private long numberOfEndpoints;
    @JsonProperty( "number_of_virtual_links" )
    private long numberOfVirtualLinks;
    @JsonProperty( "dependent_virtual_links" )
    @Valid
    private List<String> dependentVirtualLinks = new ArrayList<String>();
    @JsonProperty( "network_forwarding_path" )
    @Valid
    private List<NetworkForwardingPath> networkForwardingPath = new ArrayList<NetworkForwardingPath>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Vnffg()
    {
    }

    /**
     * @param dependentVirtualLinks
     * @param numberOfEndpoints
     * @param vnffgId
     * @param numberOfVirtualLinks
     * @param networkForwardingPath
     */
    public Vnffg( String vnffgId, long numberOfEndpoints, long numberOfVirtualLinks, List<String> dependentVirtualLinks,
        List<NetworkForwardingPath> networkForwardingPath )
    {
        this.vnffgId = vnffgId;
        this.numberOfEndpoints = numberOfEndpoints;
        this.numberOfVirtualLinks = numberOfVirtualLinks;
        this.dependentVirtualLinks = dependentVirtualLinks;
        this.networkForwardingPath = networkForwardingPath;
    }

    /**
     * @return The vnffgId
     */
    @JsonProperty( "vnffg_id" )
    public String getVnffgId()
    {
        return vnffgId;
    }

    /**
     * @param vnffgId The vnffg_id
     */
    @JsonProperty( "vnffg_id" )
    public void setVnffgId( String vnffgId )
    {
        this.vnffgId = vnffgId;
    }

    public Vnffg withVnffgId( String vnffgId )
    {
        this.vnffgId = vnffgId;
        return this;
    }

    /**
     * @return The numberOfEndpoints
     */
    @JsonProperty( "number_of_endpoints" )
    public long getNumberOfEndpoints()
    {
        return numberOfEndpoints;
    }

    /**
     * @param numberOfEndpoints The number_of_endpoints
     */
    @JsonProperty( "number_of_endpoints" )
    public void setNumberOfEndpoints( long numberOfEndpoints )
    {
        this.numberOfEndpoints = numberOfEndpoints;
    }

    public Vnffg withNumberOfEndpoints( long numberOfEndpoints )
    {
        this.numberOfEndpoints = numberOfEndpoints;
        return this;
    }

    /**
     * @return The numberOfVirtualLinks
     */
    @JsonProperty( "number_of_virtual_links" )
    public long getNumberOfVirtualLinks()
    {
        return numberOfVirtualLinks;
    }

    /**
     * @param numberOfVirtualLinks The number_of_virtual_links
     */
    @JsonProperty( "number_of_virtual_links" )
    public void setNumberOfVirtualLinks( long numberOfVirtualLinks )
    {
        this.numberOfVirtualLinks = numberOfVirtualLinks;
    }

    public Vnffg withNumberOfVirtualLinks( long numberOfVirtualLinks )
    {
        this.numberOfVirtualLinks = numberOfVirtualLinks;
        return this;
    }

    /**
     * @return The dependentVirtualLinks
     */
    @JsonProperty( "dependent_virtual_links" )
    public List<String> getDependentVirtualLinks()
    {
        return dependentVirtualLinks;
    }

    /**
     * @param dependentVirtualLinks The dependent_virtual_links
     */
    @JsonProperty( "dependent_virtual_links" )
    public void setDependentVirtualLinks( List<String> dependentVirtualLinks )
    {
        this.dependentVirtualLinks = dependentVirtualLinks;
    }

    public Vnffg withDependentVirtualLinks( List<String> dependentVirtualLinks )
    {
        this.dependentVirtualLinks = dependentVirtualLinks;
        return this;
    }

    /**
     * @return The networkForwardingPath
     */
    @JsonProperty( "network_forwarding_path" )
    public List<NetworkForwardingPath> getNetworkForwardingPath()
    {
        return networkForwardingPath;
    }

    /**
     * @param networkForwardingPath The network_forwarding_path
     */
    @JsonProperty( "network_forwarding_path" )
    public void setNetworkForwardingPath( List<NetworkForwardingPath> networkForwardingPath )
    {
        this.networkForwardingPath = networkForwardingPath;
    }

    public Vnffg withNetworkForwardingPath( List<NetworkForwardingPath> networkForwardingPath )
    {
        this.networkForwardingPath = networkForwardingPath;
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

    public Vnffg withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( vnffgId ).append( numberOfEndpoints ).append( numberOfVirtualLinks )
            .append( dependentVirtualLinks ).append( networkForwardingPath ).append( additionalProperties )
            .toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof Vnffg ) == false )
        {
            return false;
        }
        Vnffg rhs = ( (Vnffg) other );
        return new EqualsBuilder().append( vnffgId, rhs.vnffgId ).append( numberOfEndpoints, rhs.numberOfEndpoints )
            .append( numberOfVirtualLinks, rhs.numberOfVirtualLinks )
            .append( dependentVirtualLinks, rhs.dependentVirtualLinks )
            .append( networkForwardingPath, rhs.networkForwardingPath )
            .append( additionalProperties, rhs.additionalProperties ).isEquals();
    }

}
