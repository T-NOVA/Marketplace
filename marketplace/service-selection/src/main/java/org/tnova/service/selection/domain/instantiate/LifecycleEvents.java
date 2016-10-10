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
@JsonPropertyOrder( { "start", "stop", "scale_out", "scale_in" } )
public class LifecycleEvents
{

    @JsonProperty( "start" )
    @Valid
    private List<Start> start = new ArrayList<Start>();
    @JsonProperty( "stop" )
    @Valid
    private List<Object> stop = new ArrayList<Object>();
    @JsonProperty( "scale_out" )
    @Valid
    private List<Object> scaleOut = new ArrayList<Object>();
    @JsonProperty( "scale_in" )
    @Valid
    private List<Object> scaleIn = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public LifecycleEvents()
    {
    }

    /**
     * @param stop
     * @param start
     * @param scaleIn
     * @param scaleOut
     */
    public LifecycleEvents( List<Start> start, List<Object> stop, List<Object> scaleOut, List<Object> scaleIn )
    {
        this.start = start;
        this.stop = stop;
        this.scaleOut = scaleOut;
        this.scaleIn = scaleIn;
    }

    /**
     * @return The start
     */
    @JsonProperty( "start" )
    public List<Start> getStart()
    {
        return start;
    }

    /**
     * @param start The start
     */
    @JsonProperty( "start" )
    public void setStart( List<Start> start )
    {
        this.start = start;
    }

    public LifecycleEvents withStart( List<Start> start )
    {
        this.start = start;
        return this;
    }

    /**
     * @return The stop
     */
    @JsonProperty( "stop" )
    public List<Object> getStop()
    {
        return stop;
    }

    /**
     * @param stop The stop
     */
    @JsonProperty( "stop" )
    public void setStop( List<Object> stop )
    {
        this.stop = stop;
    }

    public LifecycleEvents withStop( List<Object> stop )
    {
        this.stop = stop;
        return this;
    }

    /**
     * @return The scaleOut
     */
    @JsonProperty( "scale_out" )
    public List<Object> getScaleOut()
    {
        return scaleOut;
    }

    /**
     * @param scaleOut The scale_out
     */
    @JsonProperty( "scale_out" )
    public void setScaleOut( List<Object> scaleOut )
    {
        this.scaleOut = scaleOut;
    }

    public LifecycleEvents withScaleOut( List<Object> scaleOut )
    {
        this.scaleOut = scaleOut;
        return this;
    }

    /**
     * @return The scaleIn
     */
    @JsonProperty( "scale_in" )
    public List<Object> getScaleIn()
    {
        return scaleIn;
    }

    /**
     * @param scaleIn The scale_in
     */
    @JsonProperty( "scale_in" )
    public void setScaleIn( List<Object> scaleIn )
    {
        this.scaleIn = scaleIn;
    }

    public LifecycleEvents withScaleIn( List<Object> scaleIn )
    {
        this.scaleIn = scaleIn;
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

    public LifecycleEvents withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( start ).append( stop ).append( scaleOut ).append( scaleIn )
            .append( additionalProperties ).toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof LifecycleEvents ) == false )
        {
            return false;
        }
        LifecycleEvents rhs = ( (LifecycleEvents) other );
        return new EqualsBuilder().append( start, rhs.start ).append( stop, rhs.stop ).append( scaleOut, rhs.scaleOut )
            .append( scaleIn, rhs.scaleIn ).append( additionalProperties, rhs.additionalProperties ).isEquals();
    }

}
