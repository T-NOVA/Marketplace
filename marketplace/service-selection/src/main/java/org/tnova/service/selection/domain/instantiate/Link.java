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
@JsonPropertyOrder( { "href", "rel" } )
public class Link
{

    @JsonProperty( "href" )
    private String href;
    @JsonProperty( "rel" )
    private String rel;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Link()
    {
    }

    /**
     * @param rel
     * @param href
     */
    public Link( String href, String rel )
    {
        this.href = href;
        this.rel = rel;
    }

    /**
     * @return The href
     */
    @JsonProperty( "href" )
    public String getHref()
    {
        return href;
    }

    /**
     * @param href The href
     */
    @JsonProperty( "href" )
    public void setHref( String href )
    {
        this.href = href;
    }

    public Link withHref( String href )
    {
        this.href = href;
        return this;
    }

    /**
     * @return The rel
     */
    @JsonProperty( "rel" )
    public String getRel()
    {
        return rel;
    }

    /**
     * @param rel The rel
     */
    @JsonProperty( "rel" )
    public void setRel( String rel )
    {
        this.rel = rel;
    }

    public Link withRel( String rel )
    {
        this.rel = rel;
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

    public Link withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( href ).append( rel ).append( additionalProperties ).toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof Link ) == false )
        {
            return false;
        }
        Link rhs = ( (Link) other );
        return new EqualsBuilder().append( href, rhs.href ).append( rel, rhs.rel )
            .append( additionalProperties, rhs.additionalProperties ).isEquals();
    }

}
