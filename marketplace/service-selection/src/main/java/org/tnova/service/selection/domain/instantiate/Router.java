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
@JsonPropertyOrder( { "id", "alias" } )
public class Router
{

    @JsonProperty( "id" )
    private String id;
    @JsonProperty( "alias" )
    private String alias;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Router()
    {
    }

    /**
     * @param id
     * @param alias
     */
    public Router( String id, String alias )
    {
        this.id = id;
        this.alias = alias;
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

    public Router withId( String id )
    {
        this.id = id;
        return this;
    }

    /**
     * @return The alias
     */
    @JsonProperty( "alias" )
    public String getAlias()
    {
        return alias;
    }

    /**
     * @param alias The alias
     */
    @JsonProperty( "alias" )
    public void setAlias( String alias )
    {
        this.alias = alias;
    }

    public Router withAlias( String alias )
    {
        this.alias = alias;
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

    public Router withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( id ).append( alias ).append( additionalProperties ).toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof Router ) == false )
        {
            return false;
        }
        Router rhs = ( (Router) other );
        return new EqualsBuilder().append( id, rhs.id ).append( alias, rhs.alias )
            .append( additionalProperties, rhs.additionalProperties ).isEquals();
    }

}
