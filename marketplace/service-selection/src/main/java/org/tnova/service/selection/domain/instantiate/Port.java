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
@JsonPropertyOrder( { "ns_network", "vnf_ports" } )
public class Port
{

    @JsonProperty( "ns_network" )
    private String nsNetwork;
    @JsonProperty( "vnf_ports" )
    @Valid
    private List<VnfPort> vnfPorts = new ArrayList<VnfPort>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Port()
    {
    }

    /**
     * @param nsNetwork
     * @param vnfPorts
     */
    public Port( String nsNetwork, List<VnfPort> vnfPorts )
    {
        this.nsNetwork = nsNetwork;
        this.vnfPorts = vnfPorts;
    }

    /**
     * @return The nsNetwork
     */
    @JsonProperty( "ns_network" )
    public String getNsNetwork()
    {
        return nsNetwork;
    }

    /**
     * @param nsNetwork The ns_network
     */
    @JsonProperty( "ns_network" )
    public void setNsNetwork( String nsNetwork )
    {
        this.nsNetwork = nsNetwork;
    }

    public Port withNsNetwork( String nsNetwork )
    {
        this.nsNetwork = nsNetwork;
        return this;
    }

    /**
     * @return The vnfPorts
     */
    @JsonProperty( "vnf_ports" )
    public List<VnfPort> getVnfPorts()
    {
        return vnfPorts;
    }

    /**
     * @param vnfPorts The vnf_ports
     */
    @JsonProperty( "vnf_ports" )
    public void setVnfPorts( List<VnfPort> vnfPorts )
    {
        this.vnfPorts = vnfPorts;
    }

    public Port withVnfPorts( List<VnfPort> vnfPorts )
    {
        this.vnfPorts = vnfPorts;
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

    public Port withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( nsNetwork ).append( vnfPorts ).append( additionalProperties ).toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof Port ) == false )
        {
            return false;
        }
        Port rhs = ( (Port) other );
        return new EqualsBuilder().append( nsNetwork, rhs.nsNetwork ).append( vnfPorts, rhs.vnfPorts )
            .append( additionalProperties, rhs.additionalProperties ).isEquals();
    }

}
