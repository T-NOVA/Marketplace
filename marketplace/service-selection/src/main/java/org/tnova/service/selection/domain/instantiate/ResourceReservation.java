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
@JsonPropertyOrder( { "ports", "network_stack", "routers", "networks", "public_network_id", "dns_server", "pop_id" } )
public class ResourceReservation
{

    @JsonProperty( "ports" )
    @Valid
    private List<Port> ports = new ArrayList<Port>();

    @JsonProperty( "network_stack" )
    @Valid
    private NetworkStack networkStack;

    @JsonProperty( "routers" )
    @Valid
    private List<Router> routers = new ArrayList<Router>();

    @JsonProperty( "networks" )
    @Valid
    private List<Network> networks = new ArrayList<Network>();

    @JsonProperty( "public_network_id" )
    private String publicNetworkId;

    @JsonProperty( "dns_server" )
    private List<String> dnsServer;

    @JsonProperty( "pop_id" )
    private String popId;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public ResourceReservation()
    {
    }

    /**
     * @param networks
     * @param ports
     * @param dnsServer
     * @param routers
     * @param publicNetworkId
     * @param networkStack
     * @param popId
     */
    public ResourceReservation( List<Port> ports, NetworkStack networkStack, List<Router> routers,
        List<Network> networks, String publicNetworkId, List<String> dnsServer, String popId )
    {
        this.ports = ports;
        this.networkStack = networkStack;
        this.routers = routers;
        this.networks = networks;
        this.publicNetworkId = publicNetworkId;
        this.dnsServer = dnsServer;
        this.popId = popId;
    }

    /**
     * @return The ports
     */
    @JsonProperty( "ports" )
    public List<Port> getPorts()
    {
        return ports;
    }

    /**
     * @param ports The ports
     */
    @JsonProperty( "ports" )
    public void setPorts( List<Port> ports )
    {
        this.ports = ports;
    }

    public ResourceReservation withPorts( List<Port> ports )
    {
        this.ports = ports;
        return this;
    }

    /**
     * @return The networkStack
     */
    @JsonProperty( "network_stack" )
    public NetworkStack getNetworkStack()
    {
        return networkStack;
    }

    /**
     * @param networkStack The network_stack
     */
    @JsonProperty( "network_stack" )
    public void setNetworkStack( NetworkStack networkStack )
    {
        this.networkStack = networkStack;
    }

    public ResourceReservation withNetworkStack( NetworkStack networkStack )
    {
        this.networkStack = networkStack;
        return this;
    }

    /**
     * @return The routers
     */
    @JsonProperty( "routers" )
    public List<Router> getRouters()
    {
        return routers;
    }

    /**
     * @param routers The routers
     */
    @JsonProperty( "routers" )
    public void setRouters( List<Router> routers )
    {
        this.routers = routers;
    }

    public ResourceReservation withRouters( List<Router> routers )
    {
        this.routers = routers;
        return this;
    }

    /**
     * @return The networks
     */
    @JsonProperty( "networks" )
    public List<Network> getNetworks()
    {
        return networks;
    }

    /**
     * @param networks The networks
     */
    @JsonProperty( "networks" )
    public void setNetworks( List<Network> networks )
    {
        this.networks = networks;
    }

    public ResourceReservation withNetworks( List<Network> networks )
    {
        this.networks = networks;
        return this;
    }

    /**
     * @return The publicNetworkId
     */
    @JsonProperty( "public_network_id" )
    public String getPublicNetworkId()
    {
        return publicNetworkId;
    }

    /**
     * @param publicNetworkId The public_network_id
     */
    @JsonProperty( "public_network_id" )
    public void setPublicNetworkId( String publicNetworkId )
    {
        this.publicNetworkId = publicNetworkId;
    }

    public ResourceReservation withPublicNetworkId( String publicNetworkId )
    {
        this.publicNetworkId = publicNetworkId;
        return this;
    }

    /**
     * @return The dnsServer
     */
    @JsonProperty( "dns_server" )
    public List<String> getDnsServer()
    {
        return dnsServer;
    }

    /**
     * @param dnsServer The dns_server
     */
    @JsonProperty( "dns_server" )
    public void setDnsServer( List<String> dnsServer )
    {
        this.dnsServer = dnsServer;
    }

    public ResourceReservation withDnsServer( List<String> dnsServer )
    {
        this.dnsServer = dnsServer;
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

    public ResourceReservation withPopId( String popId )
    {
        this.popId = popId;
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

    public ResourceReservation withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( ports ).append( networkStack ).append( routers ).append( networks )
            .append( publicNetworkId ).append( dnsServer ).append( popId ).append( additionalProperties ).toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof ResourceReservation ) == false )
        {
            return false;
        }
        ResourceReservation rhs = ( (ResourceReservation) other );
        return new EqualsBuilder().append( ports, rhs.ports ).append( networkStack, rhs.networkStack )
            .append( routers, rhs.routers ).append( networks, rhs.networks )
            .append( publicNetworkId, rhs.publicNetworkId ).append( dnsServer, rhs.dnsServer )
            .append( popId, rhs.popId ).append( additionalProperties, rhs.additionalProperties ).isEquals();
    }

}
