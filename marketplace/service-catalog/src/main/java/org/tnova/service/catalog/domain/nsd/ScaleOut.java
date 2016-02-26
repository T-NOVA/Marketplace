
/*
 * Copyright 2016  CloudStreet Oy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tnova.service.catalog.domain.nsd;

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
public class ScaleOut {

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
    public ScaleOut() {
    }

    /**
     * 
     * @param vnfEvent
     * @param vnfId
     */
    public ScaleOut(String vnfId, String vnfEvent) {
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

    public ScaleOut withVnfId(String vnfId) {
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

    public ScaleOut withVnfEvent(String vnfEvent) {
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

    public ScaleOut withAdditionalProperty(String name, Object value) {
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
        if ((other instanceof ScaleOut) == false) {
            return false;
        }
        ScaleOut rhs = ((ScaleOut) other);
        return new EqualsBuilder().append(vnfId, rhs.vnfId).append(vnfEvent, rhs.vnfEvent).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
