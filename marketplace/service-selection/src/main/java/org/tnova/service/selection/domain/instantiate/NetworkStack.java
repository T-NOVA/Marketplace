package org.tnova.service.selection.domain.instantiate;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@JsonInclude( JsonInclude.Include.NON_NULL )
@Generated( "org.jsonschema2pojo" )
@JsonPropertyOrder( { "stack" } )
public class NetworkStack
{

    @JsonProperty( "stack" )
    @Valid
    private Stack stack;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public NetworkStack()
    {
    }

    /**
     * @param stack
     */
    public NetworkStack( Stack stack )
    {
        this.stack = stack;
    }

    /**
     * @return The stack
     */
    @JsonProperty( "stack" )
    public Stack getStack()
    {
        return stack;
    }

    /**
     * @param stack The stack
     */
    @JsonProperty( "stack" )
    public void setStack( Stack stack )
    {
        this.stack = stack;
    }

    public NetworkStack withStack( Stack stack )
    {
        this.stack = stack;
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

    public NetworkStack withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( stack ).append( additionalProperties ).toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof NetworkStack ) == false )
        {
            return false;
        }
        NetworkStack rhs = ( (NetworkStack) other );
        return new EqualsBuilder().append( stack, rhs.stack ).append( additionalProperties, rhs.additionalProperties )
            .isEquals();
    }

}
