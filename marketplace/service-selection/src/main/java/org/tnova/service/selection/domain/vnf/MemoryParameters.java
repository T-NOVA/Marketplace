
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
    "large_pages_required",
    "numa_allocation_policy"
})
public class MemoryParameters {

    @JsonProperty("large_pages_required")
    private boolean largePagesRequired;
    @JsonProperty("numa_allocation_policy")
    private String numaAllocationPolicy;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public MemoryParameters() {
    }

    /**
     * 
     * @param largePagesRequired
     * @param numaAllocationPolicy
     */
    public MemoryParameters(boolean largePagesRequired, String numaAllocationPolicy) {
        this.largePagesRequired = largePagesRequired;
        this.numaAllocationPolicy = numaAllocationPolicy;
    }

    /**
     * 
     * @return
     *     The largePagesRequired
     */
    @JsonProperty("large_pages_required")
    public boolean isLargePagesRequired() {
        return largePagesRequired;
    }

    /**
     * 
     * @param largePagesRequired
     *     The large_pages_required
     */
    @JsonProperty("large_pages_required")
    public void setLargePagesRequired(boolean largePagesRequired) {
        this.largePagesRequired = largePagesRequired;
    }

    public MemoryParameters withLargePagesRequired(boolean largePagesRequired) {
        this.largePagesRequired = largePagesRequired;
        return this;
    }

    /**
     * 
     * @return
     *     The numaAllocationPolicy
     */
    @JsonProperty("numa_allocation_policy")
    public String getNumaAllocationPolicy() {
        return numaAllocationPolicy;
    }

    /**
     * 
     * @param numaAllocationPolicy
     *     The numa_allocation_policy
     */
    @JsonProperty("numa_allocation_policy")
    public void setNumaAllocationPolicy(String numaAllocationPolicy) {
        this.numaAllocationPolicy = numaAllocationPolicy;
    }

    public MemoryParameters withNumaAllocationPolicy(String numaAllocationPolicy) {
        this.numaAllocationPolicy = numaAllocationPolicy;
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

    public MemoryParameters withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(largePagesRequired).append(numaAllocationPolicy).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MemoryParameters) == false) {
            return false;
        }
        MemoryParameters rhs = ((MemoryParameters) other);
        return new EqualsBuilder().append(largePagesRequired, rhs.largePagesRequired).append(numaAllocationPolicy, rhs.numaAllocationPolicy).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
