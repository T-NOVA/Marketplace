
package org.tnova.service.selection.domain.instantiate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "criteria"
})
public class AutoScalePolicy {

    @JsonProperty("criteria")
    private List<Object> criteria = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public AutoScalePolicy() {
    }

    /**
     * 
     * @param criteria
     */
    public AutoScalePolicy(List<Object> criteria) {
        this.criteria = criteria;
    }

    /**
     * 
     * @return
     *     The criteria
     */
    @JsonProperty("criteria")
    public List<Object> getCriteria() {
        return criteria;
    }

    /**
     * 
     * @param criteria
     *     The criteria
     */
    @JsonProperty("criteria")
    public void setCriteria(List<Object> criteria) {
        this.criteria = criteria;
    }

    public AutoScalePolicy withCriteria(List<Object> criteria) {
        this.criteria = criteria;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public AutoScalePolicy withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(criteria).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AutoScalePolicy) == false) {
            return false;
        }
        AutoScalePolicy rhs = ((AutoScalePolicy) other);
        return new EqualsBuilder().append(criteria, rhs.criteria).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
