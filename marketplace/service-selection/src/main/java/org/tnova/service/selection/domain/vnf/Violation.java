
package org.tnova.service.selection.domain.vnf;

import java.util.HashMap;
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
    "interval",
    "breaches_count"
})
public class Violation {

    @JsonProperty("interval")
    private Integer interval;
    @JsonProperty("breaches_count")
    private Integer breachesCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Violation() {
    }

    /**
     * 
     * @param interval
     * @param breachesCount
     */
    public Violation(Integer interval, Integer breachesCount) {
        this.interval = interval;
        this.breachesCount = breachesCount;
    }

    /**
     * 
     * @return
     *     The interval
     */
    @JsonProperty("interval")
    public Integer getInterval() {
        return interval;
    }

    /**
     * 
     * @param interval
     *     The interval
     */
    @JsonProperty("interval")
    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Violation withInterval(Integer interval) {
        this.interval = interval;
        return this;
    }

    /**
     * 
     * @return
     *     The breachesCount
     */
    @JsonProperty("breaches_count")
    public Integer getBreachesCount() {
        return breachesCount;
    }

    /**
     * 
     * @param breachesCount
     *     The breaches_count
     */
    @JsonProperty("breaches_count")
    public void setBreachesCount(Integer breachesCount) {
        this.breachesCount = breachesCount;
    }

    public Violation withBreachesCount(Integer breachesCount) {
        this.breachesCount = breachesCount;
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

    public Violation withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(interval).append(breachesCount).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Violation) == false) {
            return false;
        }
        Violation rhs = ((Violation) other);
        return new EqualsBuilder().append(interval, rhs.interval).append(breachesCount, rhs.breachesCount).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
