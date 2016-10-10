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
@JsonPropertyOrder( { "criteria" } )
public class AutoScalePolicy
{

    @JsonProperty( "criteria" )
    @Valid
    private List<Object> criteria = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public AutoScalePolicy()
    {
    }

    /**
     * @param criteria
     */
    public AutoScalePolicy( List<Object> criteria )
    {
        this.criteria = criteria;
    }

    /**
     * @return The criteria
     */
    @JsonProperty( "criteria" )
    public List<Object> getCriteria()
    {
        return criteria;
    }

    /**
     * @param criteria The criteria
     */
    @JsonProperty( "criteria" )
    public void setCriteria( List<Object> criteria )
    {
        this.criteria = criteria;
    }

    public AutoScalePolicy withCriteria( List<Object> criteria )
    {
        this.criteria = criteria;
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

    public AutoScalePolicy withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( criteria ).append( additionalProperties ).toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof AutoScalePolicy ) == false )
        {
            return false;
        }
        AutoScalePolicy rhs = ( (AutoScalePolicy) other );
        return new EqualsBuilder().append( criteria, rhs.criteria )
            .append( additionalProperties, rhs.additionalProperties ).isEquals();
    }

}
