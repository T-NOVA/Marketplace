
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
    "average",
    "peak",
    "burst"
})
public class Qos {

    @JsonProperty("average")
    private String average;
    @JsonProperty("peak")
    private String peak;
    @JsonProperty("burst")
    private String burst;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Qos() {
    }

    /**
     * 
     * @param peak
     * @param burst
     * @param average
     */
    public Qos(String average, String peak, String burst) {
        this.average = average;
        this.peak = peak;
        this.burst = burst;
    }

    /**
     * 
     * @return
     *     The average
     */
    @JsonProperty("average")
    public String getAverage() {
        return average;
    }

    /**
     * 
     * @param average
     *     The average
     */
    @JsonProperty("average")
    public void setAverage(String average) {
        this.average = average;
    }

    public Qos withAverage(String average) {
        this.average = average;
        return this;
    }

    /**
     * 
     * @return
     *     The peak
     */
    @JsonProperty("peak")
    public String getPeak() {
        return peak;
    }

    /**
     * 
     * @param peak
     *     The peak
     */
    @JsonProperty("peak")
    public void setPeak(String peak) {
        this.peak = peak;
    }

    public Qos withPeak(String peak) {
        this.peak = peak;
        return this;
    }

    /**
     * 
     * @return
     *     The burst
     */
    @JsonProperty("burst")
    public String getBurst() {
        return burst;
    }

    /**
     * 
     * @param burst
     *     The burst
     */
    @JsonProperty("burst")
    public void setBurst(String burst) {
        this.burst = burst;
    }

    public Qos withBurst(String burst) {
        this.burst = burst;
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

    public Qos withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(average).append(peak).append(burst).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Qos) == false) {
            return false;
        }
        Qos rhs = ((Qos) other);
        return new EqualsBuilder().append(average, rhs.average).append(peak, rhs.peak).append(burst, rhs.burst).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
