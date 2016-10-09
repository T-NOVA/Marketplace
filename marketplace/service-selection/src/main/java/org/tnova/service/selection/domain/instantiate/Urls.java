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
@JsonPropertyOrder( { "tenant", "keystone", "orch", "compute", "neutron", "dns" } )
public class Urls
{

    @JsonProperty( "tenant" )
    private String tenant;
    @JsonProperty( "keystone" )
    private String keystone;
    @JsonProperty( "orch" )
    private String orch;
    @JsonProperty( "compute" )
    private String compute;
    @JsonProperty( "neutron" )
    private String neutron;
    @JsonProperty( "dns" )
    private String dns;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Urls()
    {
    }

    /**
     * @param tenant
     * @param dns
     * @param orch
     * @param keystone
     * @param compute
     * @param neutron
     */
    public Urls( String tenant, String keystone, String orch, String compute, String neutron, String dns )
    {
        this.tenant = tenant;
        this.keystone = keystone;
        this.orch = orch;
        this.compute = compute;
        this.neutron = neutron;
        this.dns = dns;
    }

    /**
     * @return The tenant
     */
    @JsonProperty( "tenant" )
    public String getTenant()
    {
        return tenant;
    }

    /**
     * @param tenant The tenant
     */
    @JsonProperty( "tenant" )
    public void setTenant( String tenant )
    {
        this.tenant = tenant;
    }

    public Urls withTenant( String tenant )
    {
        this.tenant = tenant;
        return this;
    }

    /**
     * @return The keystone
     */
    @JsonProperty( "keystone" )
    public String getKeystone()
    {
        return keystone;
    }

    /**
     * @param keystone The keystone
     */
    @JsonProperty( "keystone" )
    public void setKeystone( String keystone )
    {
        this.keystone = keystone;
    }

    public Urls withKeystone( String keystone )
    {
        this.keystone = keystone;
        return this;
    }

    /**
     * @return The orch
     */
    @JsonProperty( "orch" )
    public String getOrch()
    {
        return orch;
    }

    /**
     * @param orch The orch
     */
    @JsonProperty( "orch" )
    public void setOrch( String orch )
    {
        this.orch = orch;
    }

    public Urls withOrch( String orch )
    {
        this.orch = orch;
        return this;
    }

    /**
     * @return The compute
     */
    @JsonProperty( "compute" )
    public String getCompute()
    {
        return compute;
    }

    /**
     * @param compute The compute
     */
    @JsonProperty( "compute" )
    public void setCompute( String compute )
    {
        this.compute = compute;
    }

    public Urls withCompute( String compute )
    {
        this.compute = compute;
        return this;
    }

    /**
     * @return The neutron
     */
    @JsonProperty( "neutron" )
    public String getNeutron()
    {
        return neutron;
    }

    /**
     * @param neutron The neutron
     */
    @JsonProperty( "neutron" )
    public void setNeutron( String neutron )
    {
        this.neutron = neutron;
    }

    public Urls withNeutron( String neutron )
    {
        this.neutron = neutron;
        return this;
    }

    /**
     * @return The dns
     */
    @JsonProperty( "dns" )
    public String getDns()
    {
        return dns;
    }

    /**
     * @param dns The dns
     */
    @JsonProperty( "dns" )
    public void setDns( String dns )
    {
        this.dns = dns;
    }

    public Urls withDns( String dns )
    {
        this.dns = dns;
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

    public Urls withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( tenant ).append( keystone ).append( orch ).append( compute )
            .append( neutron ).append( dns ).append( additionalProperties ).toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof Urls ) == false )
        {
            return false;
        }
        Urls rhs = ( (Urls) other );
        return new EqualsBuilder().append( tenant, rhs.tenant ).append( keystone, rhs.keystone )
            .append( orch, rhs.orch ).append( compute, rhs.compute ).append( neutron, rhs.neutron )
            .append( dns, rhs.dns ).append( additionalProperties, rhs.additionalProperties ).isEquals();
    }

}
