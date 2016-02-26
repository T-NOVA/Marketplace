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
    "vnf_id",
    "vnf_event"
})
public class Stop {

    @JsonProperty("vnf_id")
    private String vnfId;
    @JsonProperty("vnf_event")
    private String vnfEvent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Stop() {
    }

    /**
     * 
     * @param vnfEvent
     * @param vnfId
     */
    public Stop(String vnfId, String vnfEvent) {
        this.vnfId = vnfId;
        this.vnfEvent = vnfEvent;
    }

    /**
     * 
     * @return
     *     The vnfId
     */
    @JsonProperty("vnf_id")
    public String getVnfId() {
        return vnfId;
    }

    /**
     * 
     * @param vnfId
     *     The vnf_id
     */
    @JsonProperty("vnf_id")
    public void setVnfId(String vnfId) {
        this.vnfId = vnfId;
    }

    public Stop withVnfId(String vnfId) {
        this.vnfId = vnfId;
        return this;
    }

    /**
     * 
     * @return
     *     The vnfEvent
     */
    @JsonProperty("vnf_event")
    public String getVnfEvent() {
        return vnfEvent;
    }

    /**
     * 
     * @param vnfEvent
     *     The vnf_event
     */
    @JsonProperty("vnf_event")
    public void setVnfEvent(String vnfEvent) {
        this.vnfEvent = vnfEvent;
    }

    public Stop withVnfEvent(String vnfEvent) {
        this.vnfEvent = vnfEvent;
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

    public Stop withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(vnfId).append(vnfEvent).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Stop) == false) {
            return false;
        }
        Stop rhs = ((Stop) other);
        return new EqualsBuilder().append(vnfId, rhs.vnfId).append(vnfEvent, rhs.vnfEvent).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
