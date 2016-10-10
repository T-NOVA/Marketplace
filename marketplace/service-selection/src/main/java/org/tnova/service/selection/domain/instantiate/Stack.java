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
@JsonPropertyOrder( { "id", "links" } )
public class Stack
{

    @JsonProperty( "id" )
    private String id;
    @JsonProperty( "links" )
    @Valid
    private List<Link> links = new ArrayList<Link>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Stack()
    {
    }

    /**
     * @param id
     * @param links
     */
    public Stack( String id, List<Link> links )
    {
        this.id = id;
        this.links = links;
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

    public Stack withId( String id )
    {
        this.id = id;
        return this;
    }

    /**
     * @return The links
     */
    @JsonProperty( "links" )
    public List<Link> getLinks()
    {
        return links;
    }

    /**
     * @param links The links
     */
    @JsonProperty( "links" )
    public void setLinks( List<Link> links )
    {
        this.links = links;
    }

    public Stack withLinks( List<Link> links )
    {
        this.links = links;
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

    public Stack withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( id ).append( links ).append( additionalProperties ).toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof Stack ) == false )
        {
            return false;
        }
        Stack rhs = ( (Stack) other );
        return new EqualsBuilder().append( id, rhs.id ).append( links, rhs.links )
            .append( additionalProperties, rhs.additionalProperties ).isEquals();
    }

}
