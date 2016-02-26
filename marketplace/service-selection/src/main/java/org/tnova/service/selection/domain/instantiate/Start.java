
package org.tnova.service.selection.domain.instantiate;

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
    "vnf_id",
    "vnf_event",
    "event_rel_id",
    "event_id",
    "flavor_ref_id"
})
public class Start {

    @JsonProperty("vnf_id")
    private String vnfId;
    @JsonProperty("vnf_event")
    private String vnfEvent;
    @JsonProperty("event_rel_id")
    private String eventRelId;
    @JsonProperty("event_id")
    private String eventId;
    @JsonProperty("flavor_ref_id")
    private String flavorRefId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Start() {
    }

    /**
     * 
     * @param eventRelId
     * @param flavorRefId
     * @param eventId
     * @param vnfEvent
     * @param vnfId
     */
    public Start(String vnfId, String vnfEvent, String eventRelId, String eventId, String flavorRefId) {
        this.vnfId = vnfId;
        this.vnfEvent = vnfEvent;
        this.eventRelId = eventRelId;
        this.eventId = eventId;
        this.flavorRefId = flavorRefId;
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

    public Start withVnfId(String vnfId) {
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

    public Start withVnfEvent(String vnfEvent) {
        this.vnfEvent = vnfEvent;
        return this;
    }

    /**
     * 
     * @return
     *     The eventRelId
     */
    @JsonProperty("event_rel_id")
    public String getEventRelId() {
        return eventRelId;
    }

    /**
     * 
     * @param eventRelId
     *     The event_rel_id
     */
    @JsonProperty("event_rel_id")
    public void setEventRelId(String eventRelId) {
        this.eventRelId = eventRelId;
    }

    public Start withEventRelId(String eventRelId) {
        this.eventRelId = eventRelId;
        return this;
    }

    /**
     * 
     * @return
     *     The eventId
     */
    @JsonProperty("event_id")
    public String getEventId() {
        return eventId;
    }

    /**
     * 
     * @param eventId
     *     The event_id
     */
    @JsonProperty("event_id")
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Start withEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    /**
     * 
     * @return
     *     The flavorRefId
     */
    @JsonProperty("flavor_ref_id")
    public String getFlavorRefId() {
        return flavorRefId;
    }

    /**
     * 
     * @param flavorRefId
     *     The flavor_ref_id
     */
    @JsonProperty("flavor_ref_id")
    public void setFlavorRefId(String flavorRefId) {
        this.flavorRefId = flavorRefId;
    }

    public Start withFlavorRefId(String flavorRefId) {
        this.flavorRefId = flavorRefId;
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

    public Start withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(vnfId).append(vnfEvent).append(eventRelId).append(eventId).append(flavorRefId).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Start) == false) {
            return false;
        }
        Start rhs = ((Start) other);
        return new EqualsBuilder().append(vnfId, rhs.vnfId).append(vnfEvent, rhs.vnfEvent).append(eventRelId, rhs.eventRelId).append(eventId, rhs.eventId).append(flavorRefId, rhs.flavorRefId).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
