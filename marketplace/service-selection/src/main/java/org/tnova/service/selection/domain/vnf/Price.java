
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
    "min_per_period",
    "max_per_period",
    "setup",
    "unit"
})
public class Price {

    @JsonProperty("min_per_period")
    private long minPerPeriod;
    @JsonProperty("max_per_period")
    private long maxPerPeriod;
    @JsonProperty("setup")
    private long setup;
    @JsonProperty("unit")
    private String unit;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Price() {
    }

    /**
     * 
     * @param unit
     * @param maxPerPeriod
     * @param setup
     * @param minPerPeriod
     */
    public Price(long minPerPeriod, long maxPerPeriod, long setup, String unit) {
        this.minPerPeriod = minPerPeriod;
        this.maxPerPeriod = maxPerPeriod;
        this.setup = setup;
        this.unit = unit;
    }

    /**
     * 
     * @return
     *     The minPerPeriod
     */
    @JsonProperty("min_per_period")
    public long getMinPerPeriod() {
        return minPerPeriod;
    }

    /**
     * 
     * @param minPerPeriod
     *     The min_per_period
     */
    @JsonProperty("min_per_period")
    public void setMinPerPeriod(long minPerPeriod) {
        this.minPerPeriod = minPerPeriod;
    }

    public Price withMinPerPeriod(long minPerPeriod) {
        this.minPerPeriod = minPerPeriod;
        return this;
    }

    /**
     * 
     * @return
     *     The maxPerPeriod
     */
    @JsonProperty("max_per_period")
    public long getMaxPerPeriod() {
        return maxPerPeriod;
    }

    /**
     * 
     * @param maxPerPeriod
     *     The max_per_period
     */
    @JsonProperty("max_per_period")
    public void setMaxPerPeriod(long maxPerPeriod) {
        this.maxPerPeriod = maxPerPeriod;
    }

    public Price withMaxPerPeriod(long maxPerPeriod) {
        this.maxPerPeriod = maxPerPeriod;
        return this;
    }

    /**
     * 
     * @return
     *     The setup
     */
    @JsonProperty("setup")
    public long getSetup() {
        return setup;
    }

    /**
     * 
     * @param setup
     *     The setup
     */
    @JsonProperty("setup")
    public void setSetup(long setup) {
        this.setup = setup;
    }

    public Price withSetup(long setup) {
        this.setup = setup;
        return this;
    }

    /**
     * 
     * @return
     *     The unit
     */
    @JsonProperty("unit")
    public String getUnit() {
        return unit;
    }

    /**
     * 
     * @param unit
     *     The unit
     */
    @JsonProperty("unit")
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Price withUnit(String unit) {
        this.unit = unit;
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

    public Price withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(minPerPeriod).append(maxPerPeriod).append(setup).append(unit).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Price) == false) {
            return false;
        }
        Price rhs = ((Price) other);
        return new EqualsBuilder().append(minPerPeriod, rhs.minPerPeriod).append(maxPerPeriod, rhs.maxPerPeriod).append(setup, rhs.setup).append(unit, rhs.unit).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
