
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
    "id",
    "name",
    "vendor",
    "version",
    "vnfds",
    "vnffgd",
    "lifecycle_events",
    "vnf_depedency",
    "monitoring_parameters",
    "vld",
    "sla",
    "auto_scale_policy",
    "connection_points",
    "vnf_dependency",
    "provider",
    "description",
    "provider_id",
    "descriptor_version"
})
public class Nsd {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("vendor")
    private String vendor;
    @JsonProperty("version")
    private String version;
    @JsonProperty("vnfds")
    private List<String> vnfds = new ArrayList<String>();
    @JsonProperty("vnffgd")
    private Vnffgd vnffgd;
    @JsonProperty("lifecycle_events")
    private LifecycleEvents lifecycleEvents;
    @JsonProperty("vnf_depedency")
    private List<Object> vnfDepedency = new ArrayList<Object>();
    @JsonProperty("monitoring_parameters")
    private List<MonitoringParameter> monitoringParameters = new ArrayList<MonitoringParameter>();
    @JsonProperty("vld")
    private Vld vld;
    @JsonProperty("sla")
    private List<Sla> sla = new ArrayList<Sla>();
    @JsonProperty("auto_scale_policy")
    private AutoScalePolicy autoScalePolicy;
    @JsonProperty("connection_points")
    private List<Object> connectionPoints = new ArrayList<Object>();
    @JsonProperty("vnf_dependency")
    private List<Object> vnfDependency = new ArrayList<Object>();
    @JsonProperty("provider")
    private String provider;
    @JsonProperty("description")
    private String description;
    @JsonProperty("provider_id")
    private String providerId;
    @JsonProperty("descriptor_version")
    private String descriptorVersion;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Nsd() {
    }

    /**
     * 
     * @param vld
     * @param vendor
     * @param connectionPoints
     * @param descriptorVersion
     * @param provider
     * @param vnfds
     * @param vnfDependency
     * @param version
     * @param id
     * @param vnffgd
     * @param lifecycleEvents
     * @param description
     * @param monitoringParameters
     * @param name
     * @param vnfDepedency
     * @param providerId
     * @param sla
     * @param autoScalePolicy
     */
    public Nsd(String id, String name, String vendor, String version, List<String> vnfds, Vnffgd vnffgd, LifecycleEvents lifecycleEvents, List<Object> vnfDepedency, List<MonitoringParameter> monitoringParameters, Vld vld, List<Sla> sla, AutoScalePolicy autoScalePolicy, List<Object> connectionPoints, List<Object> vnfDependency, String provider, String description, String providerId, String descriptorVersion) {
        this.id = id;
        this.name = name;
        this.vendor = vendor;
        this.version = version;
        this.vnfds = vnfds;
        this.vnffgd = vnffgd;
        this.lifecycleEvents = lifecycleEvents;
        this.vnfDepedency = vnfDepedency;
        this.monitoringParameters = monitoringParameters;
        this.vld = vld;
        this.sla = sla;
        this.autoScalePolicy = autoScalePolicy;
        this.connectionPoints = connectionPoints;
        this.vnfDependency = vnfDependency;
        this.provider = provider;
        this.description = description;
        this.providerId = providerId;
        this.descriptorVersion = descriptorVersion;
    }

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Nsd withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Nsd withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 
     * @return
     *     The vendor
     */
    @JsonProperty("vendor")
    public String getVendor() {
        return vendor;
    }

    /**
     * 
     * @param vendor
     *     The vendor
     */
    @JsonProperty("vendor")
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Nsd withVendor(String vendor) {
        this.vendor = vendor;
        return this;
    }

    /**
     * 
     * @return
     *     The version
     */
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    public Nsd withVersion(String version) {
        this.version = version;
        return this;
    }

    /**
     * 
     * @return
     *     The vnfds
     */
    @JsonProperty("vnfds")
    public List<String> getVnfds() {
        return vnfds;
    }

    /**
     * 
     * @param vnfds
     *     The vnfds
     */
    @JsonProperty("vnfds")
    public void setVnfds(List<String> vnfds) {
        this.vnfds = vnfds;
    }

    public Nsd withVnfds(List<String> vnfds) {
        this.vnfds = vnfds;
        return this;
    }

    /**
     * 
     * @return
     *     The vnffgd
     */
    @JsonProperty("vnffgd")
    public Vnffgd getVnffgd() {
        return vnffgd;
    }

    /**
     * 
     * @param vnffgd
     *     The vnffgd
     */
    @JsonProperty("vnffgd")
    public void setVnffgd(Vnffgd vnffgd) {
        this.vnffgd = vnffgd;
    }

    public Nsd withVnffgd(Vnffgd vnffgd) {
        this.vnffgd = vnffgd;
        return this;
    }

    /**
     * 
     * @return
     *     The lifecycleEvents
     */
    @JsonProperty("lifecycle_events")
    public LifecycleEvents getLifecycleEvents() {
        return lifecycleEvents;
    }

    /**
     * 
     * @param lifecycleEvents
     *     The lifecycle_events
     */
    @JsonProperty("lifecycle_events")
    public void setLifecycleEvents(LifecycleEvents lifecycleEvents) {
        this.lifecycleEvents = lifecycleEvents;
    }

    public Nsd withLifecycleEvents(LifecycleEvents lifecycleEvents) {
        this.lifecycleEvents = lifecycleEvents;
        return this;
    }

    /**
     * 
     * @return
     *     The vnfDepedency
     */
    @JsonProperty("vnf_depedency")
    public List<Object> getVnfDepedency() {
        return vnfDepedency;
    }

    /**
     * 
     * @param vnfDepedency
     *     The vnf_depedency
     */
    @JsonProperty("vnf_depedency")
    public void setVnfDepedency(List<Object> vnfDepedency) {
        this.vnfDepedency = vnfDepedency;
    }

    public Nsd withVnfDepedency(List<Object> vnfDepedency) {
        this.vnfDepedency = vnfDepedency;
        return this;
    }

    /**
     * 
     * @return
     *     The monitoringParameters
     */
    @JsonProperty("monitoring_parameters")
    public List<MonitoringParameter> getMonitoringParameters() {
        return monitoringParameters;
    }

    /**
     * 
     * @param monitoringParameters
     *     The monitoring_parameters
     */
    @JsonProperty("monitoring_parameters")
    public void setMonitoringParameters(List<MonitoringParameter> monitoringParameters) {
        this.monitoringParameters = monitoringParameters;
    }

    public Nsd withMonitoringParameters(List<MonitoringParameter> monitoringParameters) {
        this.monitoringParameters = monitoringParameters;
        return this;
    }

    /**
     * 
     * @return
     *     The vld
     */
    @JsonProperty("vld")
    public Vld getVld() {
        return vld;
    }

    /**
     * 
     * @param vld
     *     The vld
     */
    @JsonProperty("vld")
    public void setVld(Vld vld) {
        this.vld = vld;
    }

    public Nsd withVld(Vld vld) {
        this.vld = vld;
        return this;
    }

    /**
     * 
     * @return
     *     The sla
     */
    @JsonProperty("sla")
    public List<Sla> getSla() {
        return sla;
    }

    /**
     * 
     * @param sla
     *     The sla
     */
    @JsonProperty("sla")
    public void setSla(List<Sla> sla) {
        this.sla = sla;
    }

    public Nsd withSla(List<Sla> sla) {
        this.sla = sla;
        return this;
    }

    /**
     * 
     * @return
     *     The autoScalePolicy
     */
    @JsonProperty("auto_scale_policy")
    public AutoScalePolicy getAutoScalePolicy() {
        return autoScalePolicy;
    }

    /**
     * 
     * @param autoScalePolicy
     *     The auto_scale_policy
     */
    @JsonProperty("auto_scale_policy")
    public void setAutoScalePolicy(AutoScalePolicy autoScalePolicy) {
        this.autoScalePolicy = autoScalePolicy;
    }

    public Nsd withAutoScalePolicy(AutoScalePolicy autoScalePolicy) {
        this.autoScalePolicy = autoScalePolicy;
        return this;
    }

    /**
     * 
     * @return
     *     The connectionPoints
     */
    @JsonProperty("connection_points")
    public List<Object> getConnectionPoints() {
        return connectionPoints;
    }

    /**
     * 
     * @param connectionPoints
     *     The connection_points
     */
    @JsonProperty("connection_points")
    public void setConnectionPoints(List<Object> connectionPoints) {
        this.connectionPoints = connectionPoints;
    }

    public Nsd withConnectionPoints(List<Object> connectionPoints) {
        this.connectionPoints = connectionPoints;
        return this;
    }

    /**
     * 
     * @return
     *     The vnfDependency
     */
    @JsonProperty("vnf_dependency")
    public List<Object> getVnfDependency() {
        return vnfDependency;
    }

    /**
     * 
     * @param vnfDependency
     *     The vnf_dependency
     */
    @JsonProperty("vnf_dependency")
    public void setVnfDependency(List<Object> vnfDependency) {
        this.vnfDependency = vnfDependency;
    }

    public Nsd withVnfDependency(List<Object> vnfDependency) {
        this.vnfDependency = vnfDependency;
        return this;
    }

    /**
     * 
     * @return
     *     The provider
     */
    @JsonProperty("provider")
    public String getProvider() {
        return provider;
    }

    /**
     * 
     * @param provider
     *     The provider
     */
    @JsonProperty("provider")
    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Nsd withProvider(String provider) {
        this.provider = provider;
        return this;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Nsd withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 
     * @return
     *     The providerId
     */
    @JsonProperty("provider_id")
    public String getProviderId() {
        return providerId;
    }

    /**
     * 
     * @param providerId
     *     The provider_id
     */
    @JsonProperty("provider_id")
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Nsd withProviderId(String providerId) {
        this.providerId = providerId;
        return this;
    }

    /**
     * 
     * @return
     *     The descriptorVersion
     */
    @JsonProperty("descriptor_version")
    public String getDescriptorVersion() {
        return descriptorVersion;
    }

    /**
     * 
     * @param descriptorVersion
     *     The descriptor_version
     */
    @JsonProperty("descriptor_version")
    public void setDescriptorVersion(String descriptorVersion) {
        this.descriptorVersion = descriptorVersion;
    }

    public Nsd withDescriptorVersion(String descriptorVersion) {
        this.descriptorVersion = descriptorVersion;
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

    public Nsd withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).append(vendor).append(version).append(vnfds).append(vnffgd).append(lifecycleEvents).append(vnfDepedency).append(monitoringParameters).append(vld).append(sla).append(autoScalePolicy).append(connectionPoints).append(vnfDependency).append(provider).append(description).append(providerId).append(descriptorVersion).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Nsd) == false) {
            return false;
        }
        Nsd rhs = ((Nsd) other);
        return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).append(vendor, rhs.vendor).append(version, rhs.version).append(vnfds, rhs.vnfds).append(vnffgd, rhs.vnffgd).append(lifecycleEvents, rhs.lifecycleEvents).append(vnfDepedency, rhs.vnfDepedency).append(monitoringParameters, rhs.monitoringParameters).append(vld, rhs.vld).append(sla, rhs.sla).append(autoScalePolicy, rhs.autoScalePolicy).append(connectionPoints, rhs.connectionPoints).append(vnfDependency, rhs.vnfDependency).append(provider, rhs.provider).append(description, rhs.description).append(providerId, rhs.providerId).append(descriptorVersion, rhs.descriptorVersion).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
