
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
    "SR-IOV",
    "device_pass_through"
})
public class PlatformPcieParameters {

    @JsonProperty("SR-IOV")
    private Boolean SRIOV;
    @JsonProperty("device_pass_through")
    private Boolean devicePassThrough;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public PlatformPcieParameters() {
    }

    /**
     * 
     * @param devicePassThrough
     * @param SRIOV
     */
    public PlatformPcieParameters(Boolean SRIOV, Boolean devicePassThrough) {
        this.SRIOV = SRIOV;
        this.devicePassThrough = devicePassThrough;
    }

    /**
     * 
     * @return
     *     The SRIOV
     */
    @JsonProperty("SR-IOV")
    public Boolean getSRIOV() {
        return SRIOV;
    }

    /**
     * 
     * @param SRIOV
     *     The SR-IOV
     */
    @JsonProperty("SR-IOV")
    public void setSRIOV(Boolean SRIOV) {
        this.SRIOV = SRIOV;
    }

    public PlatformPcieParameters withSRIOV(Boolean SRIOV) {
        this.SRIOV = SRIOV;
        return this;
    }

    /**
     * 
     * @return
     *     The devicePassThrough
     */
    @JsonProperty("device_pass_through")
    public Boolean getDevicePassThrough() {
        return devicePassThrough;
    }

    /**
     * 
     * @param devicePassThrough
     *     The device_pass_through
     */
    @JsonProperty("device_pass_through")
    public void setDevicePassThrough(Boolean devicePassThrough) {
        this.devicePassThrough = devicePassThrough;
    }

    public PlatformPcieParameters withDevicePassThrough(Boolean devicePassThrough) {
        this.devicePassThrough = devicePassThrough;
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

    public PlatformPcieParameters withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(SRIOV).append(devicePassThrough).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PlatformPcieParameters) == false) {
            return false;
        }
        PlatformPcieParameters rhs = ((PlatformPcieParameters) other);
        return new EqualsBuilder().append(SRIOV, rhs.SRIOV).append(devicePassThrough, rhs.devicePassThrough).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
