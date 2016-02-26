
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "vnffgs"
})
public class Vnffgd {

    @JsonProperty("vnffgs")
    private List<Vnffg> vnffgs = new ArrayList<Vnffg>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Vnffgd() {
    }

    /**
     * 
     * @param vnffgs
     */
    public Vnffgd(List<Vnffg> vnffgs) {
        this.vnffgs = vnffgs;
    }

    /**
     * 
     * @return
     *     The vnffgs
     */
    @JsonProperty("vnffgs")
    public List<Vnffg> getVnffgs() {
        return vnffgs;
    }

    /**
     * 
     * @param vnffgs
     *     The vnffgs
     */
    @JsonProperty("vnffgs")
    public void setVnffgs(List<Vnffg> vnffgs) {
        this.vnffgs = vnffgs;
    }

    public Vnffgd withVnffgs(List<Vnffg> vnffgs) {
        this.vnffgs = vnffgs;
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

    public Vnffgd withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(vnffgs).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Vnffgd) == false) {
            return false;
        }
        Vnffgd rhs = ((Vnffgd) other);
        return new EqualsBuilder().append(vnffgs, rhs.vnffgs).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
