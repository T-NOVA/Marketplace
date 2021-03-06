
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
    "vnffg_id",
    "number_of_endpoints",
    "number_of_virtual_links",
    "dependent_virtual_links",
    "network_forwarding_path"
})
public class Vnffg {

    @JsonProperty("vnffg_id")
    private String vnffgId;
    @JsonProperty("number_of_endpoints")
    private long numberOfEndpoints;
    @JsonProperty("number_of_virtual_links")
    private long numberOfVirtualLinks;
    @JsonProperty("dependent_virtual_links")
    private List<String> dependentVirtualLinks = new ArrayList<String>();
    @JsonProperty("network_forwarding_path")
    private List<NetworkForwardingPath> networkForwardingPath = new ArrayList<NetworkForwardingPath>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Vnffg() {
    }

    /**
     * 
     * @param dependentVirtualLinks
     * @param numberOfEndpoints
     * @param vnffgId
     * @param numberOfVirtualLinks
     * @param networkForwardingPath
     */
    public Vnffg(String vnffgId, long numberOfEndpoints, long numberOfVirtualLinks, List<String> dependentVirtualLinks, List<NetworkForwardingPath> networkForwardingPath) {
        this.vnffgId = vnffgId;
        this.numberOfEndpoints = numberOfEndpoints;
        this.numberOfVirtualLinks = numberOfVirtualLinks;
        this.dependentVirtualLinks = dependentVirtualLinks;
        this.networkForwardingPath = networkForwardingPath;
    }

    /**
     * 
     * @return
     *     The vnffgId
     */
    @JsonProperty("vnffg_id")
    public String getVnffgId() {
        return vnffgId;
    }

    /**
     * 
     * @param vnffgId
     *     The vnffg_id
     */
    @JsonProperty("vnffg_id")
    public void setVnffgId(String vnffgId) {
        this.vnffgId = vnffgId;
    }

    public Vnffg withVnffgId(String vnffgId) {
        this.vnffgId = vnffgId;
        return this;
    }

    /**
     * 
     * @return
     *     The numberOfEndpoints
     */
    @JsonProperty("number_of_endpoints")
    public long getNumberOfEndpoints() {
        return numberOfEndpoints;
    }

    /**
     * 
     * @param numberOfEndpoints
     *     The number_of_endpoints
     */
    @JsonProperty("number_of_endpoints")
    public void setNumberOfEndpoints(long numberOfEndpoints) {
        this.numberOfEndpoints = numberOfEndpoints;
    }

    public Vnffg withNumberOfEndpoints(long numberOfEndpoints) {
        this.numberOfEndpoints = numberOfEndpoints;
        return this;
    }

    /**
     * 
     * @return
     *     The numberOfVirtualLinks
     */
    @JsonProperty("number_of_virtual_links")
    public long getNumberOfVirtualLinks() {
        return numberOfVirtualLinks;
    }

    /**
     * 
     * @param numberOfVirtualLinks
     *     The number_of_virtual_links
     */
    @JsonProperty("number_of_virtual_links")
    public void setNumberOfVirtualLinks(long numberOfVirtualLinks) {
        this.numberOfVirtualLinks = numberOfVirtualLinks;
    }

    public Vnffg withNumberOfVirtualLinks(long numberOfVirtualLinks) {
        this.numberOfVirtualLinks = numberOfVirtualLinks;
        return this;
    }

    /**
     * 
     * @return
     *     The dependentVirtualLinks
     */
    @JsonProperty("dependent_virtual_links")
    public List<String> getDependentVirtualLinks() {
        return dependentVirtualLinks;
    }

    /**
     * 
     * @param dependentVirtualLinks
     *     The dependent_virtual_links
     */
    @JsonProperty("dependent_virtual_links")
    public void setDependentVirtualLinks(List<String> dependentVirtualLinks) {
        this.dependentVirtualLinks = dependentVirtualLinks;
    }

    public Vnffg withDependentVirtualLinks(List<String> dependentVirtualLinks) {
        this.dependentVirtualLinks = dependentVirtualLinks;
        return this;
    }

    /**
     * 
     * @return
     *     The networkForwardingPath
     */
    @JsonProperty("network_forwarding_path")
    public List<NetworkForwardingPath> getNetworkForwardingPath() {
        return networkForwardingPath;
    }

    /**
     * 
     * @param networkForwardingPath
     *     The network_forwarding_path
     */
    @JsonProperty("network_forwarding_path")
    public void setNetworkForwardingPath(List<NetworkForwardingPath> networkForwardingPath) {
        this.networkForwardingPath = networkForwardingPath;
    }

    public Vnffg withNetworkForwardingPath(List<NetworkForwardingPath> networkForwardingPath) {
        this.networkForwardingPath = networkForwardingPath;
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

    public Vnffg withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(vnffgId).append(numberOfEndpoints).append(numberOfVirtualLinks).append(dependentVirtualLinks).append(networkForwardingPath).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Vnffg) == false) {
            return false;
        }
        Vnffg rhs = ((Vnffg) other);
        return new EqualsBuilder().append(vnffgId, rhs.vnffgId).append(numberOfEndpoints, rhs.numberOfEndpoints).append(numberOfVirtualLinks, rhs.numberOfVirtualLinks).append(dependentVirtualLinks, rhs.dependentVirtualLinks).append(networkForwardingPath, rhs.networkForwardingPath).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
