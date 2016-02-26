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
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "end-to-end bandwidth",
    "test"
})
public class Criterium {

    @JsonProperty("end-to-end bandwidth")
    private String endToEndBandwidth;
    @JsonProperty("test")
    private String test;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Criterium() {
    }

    /**
     * 
     * @param endToEndBandwidth
     * @param test
     */
    public Criterium(String endToEndBandwidth, String test) {
        this.endToEndBandwidth = endToEndBandwidth;
        this.test = test;
    }

    /**
     * 
     * @return
     *     The endToEndBandwidth
     */
    @JsonProperty("end-to-end bandwidth")
    public String getEndToEndBandwidth() {
        return endToEndBandwidth;
    }

    /**
     * 
     * @param endToEndBandwidth
     *     The end-to-end bandwidth
     */
    @JsonProperty("end-to-end bandwidth")
    public void setEndToEndBandwidth(String endToEndBandwidth) {
        this.endToEndBandwidth = endToEndBandwidth;
    }

    public Criterium withEndToEndBandwidth(String endToEndBandwidth) {
        this.endToEndBandwidth = endToEndBandwidth;
        return this;
    }

    /**
     * 
     * @return
     *     The test
     */
    @JsonProperty("test")
    public String getTest() {
        return test;
    }

    /**
     * 
     * @param test
     *     The test
     */
    @JsonProperty("test")
    public void setTest(String test) {
        this.test = test;
    }

    public Criterium withTest(String test) {
        this.test = test;
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

    public Criterium withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(endToEndBandwidth).append(test).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Criterium) == false) {
            return false;
        }
        Criterium rhs = ((Criterium) other);
        return new EqualsBuilder().append(endToEndBandwidth, rhs.endToEndBandwidth).append(test, rhs.test).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
