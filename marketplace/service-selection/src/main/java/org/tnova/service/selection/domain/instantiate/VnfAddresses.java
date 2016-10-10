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
@JsonPropertyOrder( { "controller", "vdu0#CPr00t#PublicIp" } )
public class VnfAddresses
{

    @JsonProperty( "controller" )
    private String controller;
    @JsonProperty( "vdu0#CPr00t#PublicIp" )
    private String vdu0CPr00tPublicIp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public VnfAddresses()
    {
    }

    /**
     * @param vdu0CPr00tPublicIp
     * @param controller
     */
    public VnfAddresses( String controller, String vdu0CPr00tPublicIp )
    {
        this.controller = controller;
        this.vdu0CPr00tPublicIp = vdu0CPr00tPublicIp;
    }

    /**
     * @return The controller
     */
    @JsonProperty( "controller" )
    public String getController()
    {
        return controller;
    }

    /**
     * @param controller The controller
     */
    @JsonProperty( "controller" )
    public void setController( String controller )
    {
        this.controller = controller;
    }

    public VnfAddresses withController( String controller )
    {
        this.controller = controller;
        return this;
    }

    /**
     * @return The vdu0CPr00tPublicIp
     */
    @JsonProperty( "vdu0#CPr00t#PublicIp" )
    public String getVdu0CPr00tPublicIp()
    {
        return vdu0CPr00tPublicIp;
    }

    /**
     * @param vdu0CPr00tPublicIp The vdu0#CPr00t#PublicIp
     */
    @JsonProperty( "vdu0#CPr00t#PublicIp" )
    public void setVdu0CPr00tPublicIp( String vdu0CPr00tPublicIp )
    {
        this.vdu0CPr00tPublicIp = vdu0CPr00tPublicIp;
    }

    public VnfAddresses withVdu0CPr00tPublicIp( String vdu0CPr00tPublicIp )
    {
        this.vdu0CPr00tPublicIp = vdu0CPr00tPublicIp;
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

    public VnfAddresses withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( controller ).append( vdu0CPr00tPublicIp ).append( additionalProperties )
            .toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof VnfAddresses ) == false )
        {
            return false;
        }
        VnfAddresses rhs = ( (VnfAddresses) other );
        return new EqualsBuilder().append( controller, rhs.controller )
            .append( vdu0CPr00tPublicIp, rhs.vdu0CPr00tPublicIp )
            .append( additionalProperties, rhs.additionalProperties ).isEquals();
    }

}
