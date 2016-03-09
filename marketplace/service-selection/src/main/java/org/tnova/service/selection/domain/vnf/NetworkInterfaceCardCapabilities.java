
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
    "mirroring"
})
public class NetworkInterfaceCardCapabilities {

    @JsonProperty("SR-IOV")
    private Boolean SRIOV;
    @JsonProperty("mirroring")
    private Boolean mirroring;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public NetworkInterfaceCardCapabilities() {
    }

    /**
     * 
     * @param SRIOV
     * @param mirroring
     */
    public NetworkInterfaceCardCapabilities(Boolean SRIOV, Boolean mirroring) {
        this.SRIOV = SRIOV;
        this.mirroring = mirroring;
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

    public NetworkInterfaceCardCapabilities withSRIOV(Boolean SRIOV) {
        this.SRIOV = SRIOV;
        return this;
    }

    /**
     * 
     * @return
     *     The mirroring
     */
    @JsonProperty("mirroring")
    public Boolean getMirroring() {
        return mirroring;
    }

    /**
     * 
     * @param mirroring
     *     The mirroring
     */
    @JsonProperty("mirroring")
    public void setMirroring(Boolean mirroring) {
        this.mirroring = mirroring;
    }

    public NetworkInterfaceCardCapabilities withMirroring(Boolean mirroring) {
        this.mirroring = mirroring;
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

    public NetworkInterfaceCardCapabilities withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(SRIOV).append(mirroring).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NetworkInterfaceCardCapabilities) == false) {
            return false;
        }
        NetworkInterfaceCardCapabilities rhs = ((NetworkInterfaceCardCapabilities) other);
        return new EqualsBuilder().append(SRIOV, rhs.SRIOV).append(mirroring, rhs.mirroring).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
