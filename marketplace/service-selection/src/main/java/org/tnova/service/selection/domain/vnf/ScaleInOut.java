
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
    "minimum",
    "maximum"
})
public class ScaleInOut {

    @JsonProperty("minimum")
    private long minimum;
    @JsonProperty("maximum")
    private long maximum;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ScaleInOut() {
    }

    /**
     * 
     * @param minimum
     * @param maximum
     */
    public ScaleInOut(long minimum, long maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    /**
     * 
     * @return
     *     The minimum
     */
    @JsonProperty("minimum")
    public long getMinimum() {
        return minimum;
    }

    /**
     * 
     * @param minimum
     *     The minimum
     */
    @JsonProperty("minimum")
    public void setMinimum(long minimum) {
        this.minimum = minimum;
    }

    public ScaleInOut withMinimum(long minimum) {
        this.minimum = minimum;
        return this;
    }

    /**
     * 
     * @return
     *     The maximum
     */
    @JsonProperty("maximum")
    public long getMaximum() {
        return maximum;
    }

    /**
     * 
     * @param maximum
     *     The maximum
     */
    @JsonProperty("maximum")
    public void setMaximum(long maximum) {
        this.maximum = maximum;
    }

    public ScaleInOut withMaximum(long maximum) {
        this.maximum = maximum;
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

    public ScaleInOut withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(minimum).append(maximum).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ScaleInOut) == false) {
            return false;
        }
        ScaleInOut rhs = ((ScaleInOut) other);
        return new EqualsBuilder().append(minimum, rhs.minimum).append(maximum, rhs.maximum).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
