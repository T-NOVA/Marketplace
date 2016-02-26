
package org.tnova.service.selection.domain.nsd;

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
    "breaches_count",
    "interval"
})
public class Violation {

    @JsonProperty("breaches_count")
    private long breachesCount;
    @JsonProperty("interval")
    private long interval;
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
    public Violation(long breachesCount, long interval) {
        this.breachesCount = breachesCount;
        this.interval = interval;
    }

    /**
     * 
     * @return
     *     The breachesCount
     */
    @JsonProperty("breaches_count")
    public long getBreachesCount() {
        return breachesCount;
    }

    /**
     * 
     * @param breachesCount
     *     The breaches_count
     */
    @JsonProperty("breaches_count")
    public void setBreachesCount(long breachesCount) {
        this.breachesCount = breachesCount;
    }

    public Violation withBreachesCount(long breachesCount) {
        this.breachesCount = breachesCount;
        return this;
    }

    /**
     * 
     * @return
     *     The interval
     */
    @JsonProperty("interval")
    public long getInterval() {
        return interval;
    }

    /**
     * 
     * @param interval
     *     The interval
     */
    @JsonProperty("interval")
    public void setInterval(long interval) {
        this.interval = interval;
    }

    public Violation withInterval(long interval) {
        this.interval = interval;
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
        return new HashCodeBuilder().append(breachesCount).append(interval).append(additionalProperties).toHashCode();
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
        return new EqualsBuilder().append(breachesCount, rhs.breachesCount).append(interval, rhs.interval).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
