
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
    "vld_id",
    "alias",
    "root_requirements",
    "leaf_requirement",
    "qos",
    "connections",
    "connectivity_type",
    "external_access",
    "merge",
    "sla_ref_id"
})
public class VirtualLink {

    @JsonProperty("vld_id")
    private String vldId;
    @JsonProperty("alias")
    private String alias;
    @JsonProperty("root_requirements")
    private String rootRequirements;
    @JsonProperty("leaf_requirement")
    private String leafRequirement;
    @JsonProperty("qos")
    private Qos qos;
    @JsonProperty("connections")
    private List<String> connections = new ArrayList<String>();
    @JsonProperty("connectivity_type")
    private String connectivityType;
    @JsonProperty("external_access")
    private boolean externalAccess;
    @JsonProperty("merge")
    private boolean merge;
    @JsonProperty("sla_ref_id")
    private String slaRefId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public VirtualLink() {
    }

    /**
     * 
     * @param qos
     * @param leafRequirement
     * @param alias
     * @param merge
     * @param slaRefId
     * @param externalAccess
     * @param rootRequirements
     * @param connections
     * @param vldId
     * @param connectivityType
     */
    public VirtualLink(String vldId, String alias, String rootRequirements, String leafRequirement, Qos qos, List<String> connections, String connectivityType, boolean externalAccess, boolean merge, String slaRefId) {
        this.vldId = vldId;
        this.alias = alias;
        this.rootRequirements = rootRequirements;
        this.leafRequirement = leafRequirement;
        this.qos = qos;
        this.connections = connections;
        this.connectivityType = connectivityType;
        this.externalAccess = externalAccess;
        this.merge = merge;
        this.slaRefId = slaRefId;
    }

    /**
     * 
     * @return
     *     The vldId
     */
    @JsonProperty("vld_id")
    public String getVldId() {
        return vldId;
    }

    /**
     * 
     * @param vldId
     *     The vld_id
     */
    @JsonProperty("vld_id")
    public void setVldId(String vldId) {
        this.vldId = vldId;
    }

    public VirtualLink withVldId(String vldId) {
        this.vldId = vldId;
        return this;
    }

    /**
     * 
     * @return
     *     The alias
     */
    @JsonProperty("alias")
    public String getAlias() {
        return alias;
    }

    /**
     * 
     * @param alias
     *     The alias
     */
    @JsonProperty("alias")
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public VirtualLink withAlias(String alias) {
        this.alias = alias;
        return this;
    }

    /**
     * 
     * @return
     *     The rootRequirements
     */
    @JsonProperty("root_requirements")
    public String getRootRequirements() {
        return rootRequirements;
    }

    /**
     * 
     * @param rootRequirements
     *     The root_requirements
     */
    @JsonProperty("root_requirements")
    public void setRootRequirements(String rootRequirements) {
        this.rootRequirements = rootRequirements;
    }

    public VirtualLink withRootRequirements(String rootRequirements) {
        this.rootRequirements = rootRequirements;
        return this;
    }

    /**
     * 
     * @return
     *     The leafRequirement
     */
    @JsonProperty("leaf_requirement")
    public String getLeafRequirement() {
        return leafRequirement;
    }

    /**
     * 
     * @param leafRequirement
     *     The leaf_requirement
     */
    @JsonProperty("leaf_requirement")
    public void setLeafRequirement(String leafRequirement) {
        this.leafRequirement = leafRequirement;
    }

    public VirtualLink withLeafRequirement(String leafRequirement) {
        this.leafRequirement = leafRequirement;
        return this;
    }

    /**
     * 
     * @return
     *     The qos
     */
    @JsonProperty("qos")
    public Qos getQos() {
        return qos;
    }

    /**
     * 
     * @param qos
     *     The qos
     */
    @JsonProperty("qos")
    public void setQos(Qos qos) {
        this.qos = qos;
    }

    public VirtualLink withQos(Qos qos) {
        this.qos = qos;
        return this;
    }

    /**
     * 
     * @return
     *     The connections
     */
    @JsonProperty("connections")
    public List<String> getConnections() {
        return connections;
    }

    /**
     * 
     * @param connections
     *     The connections
     */
    @JsonProperty("connections")
    public void setConnections(List<String> connections) {
        this.connections = connections;
    }

    public VirtualLink withConnections(List<String> connections) {
        this.connections = connections;
        return this;
    }

    /**
     * 
     * @return
     *     The connectivityType
     */
    @JsonProperty("connectivity_type")
    public String getConnectivityType() {
        return connectivityType;
    }

    /**
     * 
     * @param connectivityType
     *     The connectivity_type
     */
    @JsonProperty("connectivity_type")
    public void setConnectivityType(String connectivityType) {
        this.connectivityType = connectivityType;
    }

    public VirtualLink withConnectivityType(String connectivityType) {
        this.connectivityType = connectivityType;
        return this;
    }

    /**
     * 
     * @return
     *     The externalAccess
     */
    @JsonProperty("external_access")
    public boolean isExternalAccess() {
        return externalAccess;
    }

    /**
     * 
     * @param externalAccess
     *     The external_access
     */
    @JsonProperty("external_access")
    public void setExternalAccess(boolean externalAccess) {
        this.externalAccess = externalAccess;
    }

    public VirtualLink withExternalAccess(boolean externalAccess) {
        this.externalAccess = externalAccess;
        return this;
    }

    /**
     * 
     * @return
     *     The merge
     */
    @JsonProperty("merge")
    public boolean isMerge() {
        return merge;
    }

    /**
     * 
     * @param merge
     *     The merge
     */
    @JsonProperty("merge")
    public void setMerge(boolean merge) {
        this.merge = merge;
    }

    public VirtualLink withMerge(boolean merge) {
        this.merge = merge;
        return this;
    }

    /**
     * 
     * @return
     *     The slaRefId
     */
    @JsonProperty("sla_ref_id")
    public String getSlaRefId() {
        return slaRefId;
    }

    /**
     * 
     * @param slaRefId
     *     The sla_ref_id
     */
    @JsonProperty("sla_ref_id")
    public void setSlaRefId(String slaRefId) {
        this.slaRefId = slaRefId;
    }

    public VirtualLink withSlaRefId(String slaRefId) {
        this.slaRefId = slaRefId;
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

    public VirtualLink withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(vldId).append(alias).append(rootRequirements).append(leafRequirement).append(qos).append(connections).append(connectivityType).append(externalAccess).append(merge).append(slaRefId).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VirtualLink) == false) {
            return false;
        }
        VirtualLink rhs = ((VirtualLink) other);
        return new EqualsBuilder().append(vldId, rhs.vldId).append(alias, rhs.alias).append(rootRequirements, rhs.rootRequirements).append(leafRequirement, rhs.leafRequirement).append(qos, rhs.qos).append(connections, rhs.connections).append(connectivityType, rhs.connectivityType).append(externalAccess, rhs.externalAccess).append(merge, rhs.merge).append(slaRefId, rhs.slaRefId).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
