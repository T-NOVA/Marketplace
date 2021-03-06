
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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "vnf_ref_id",
    "vnf_flavor_key_ref"
})
public class ConstituentVnf {

    @JsonProperty("vnf_ref_id")
    private String vnfRefId;
    @JsonProperty("vnf_flavor_key_ref")
    private String vnfFlavorKeyRef;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ConstituentVnf() {
    }

    public ConstituentVnf(String vnfRefId, String vnfFlavorKeyRef) {
        this.vnfRefId = vnfRefId;
        this.vnfFlavorKeyRef = vnfFlavorKeyRef;
    }

    /**
     * 
     * @return
     *     The vnfRefId
     */
    @JsonProperty("vnf_ref_id")
    public String getVnfRefId() {
        return vnfRefId;
    }

    /**
     * 
     * @param vnfRefId
     *     The vnf_ref_id
     */
    @JsonProperty("vnf_ref_id")
    public void setVnfRefId(String vnfRefId) {
        this.vnfRefId = vnfRefId;
    }

    public ConstituentVnf withVnfRefId(String vnfRefId) {
        this.vnfRefId = vnfRefId;
        return this;
    }

    /**
     * 
     * @return
     *     The vnfFlavorKeyRef
     */
    @JsonProperty("vnf_flavor_key_ref")
    public String getVnfFlavorKeyRef() {
        return vnfFlavorKeyRef;
    }

    /**
     * 
     * @param vnfFlavorKeyRef
     *     The vnf_flavor_key_ref
     */
    @JsonProperty("vnf_flavor_key_ref")
    public void setVnfFlavorKeyRef(String vnfFlavorKeyRef) {
        this.vnfFlavorKeyRef = vnfFlavorKeyRef;
    }

    public ConstituentVnf withVnfFlavorKeyRef(String vnfFlavorKeyRef) {
        this.vnfFlavorKeyRef = vnfFlavorKeyRef;
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

    public ConstituentVnf withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(vnfRefId).append(vnfFlavorKeyRef).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ConstituentVnf) == false) {
            return false;
        }
        ConstituentVnf rhs = ((ConstituentVnf) other);
        return new EqualsBuilder().append(vnfRefId, rhs.vnfRefId).append(vnfFlavorKeyRef, rhs.vnfFlavorKeyRef).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
