
package org.tnova.service.selection.domain.vnf;

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
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "provider_id",
    "vdu",
    "name",
    "created_at",
    "modified_at",
    "vlinks",
    "trade",
    "descriptor_version",
    "deployment_flavours",
    "version",
    "vnf_lifecycle_events",
    "billing_model",
    "provider",
    "release",
    "type",
    "id",
    "description"
})
public class VnfDescriptor {

    @JsonProperty("provider_id")
    private Integer providerId;
    @JsonProperty("vdu")
    private List<Vdu> vdu = new ArrayList<Vdu>();
    @JsonProperty("name")
    private String name;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("modified_at")
    private String modifiedAt;
    @JsonProperty("vlinks")
    private List<Vlink> vlinks = new ArrayList<Vlink>();
    @JsonProperty("trade")
    private Boolean trade;
    @JsonProperty("descriptor_version")
    private String descriptorVersion;
    @JsonProperty("deployment_flavours")
    private List<DeploymentFlavour> deploymentFlavours = new ArrayList<DeploymentFlavour>();
    @JsonProperty("version")
    private String version;
    @JsonProperty("vnf_lifecycle_events")
    private List<VnfLifecycleEvent> vnfLifecycleEvents = new ArrayList<VnfLifecycleEvent>();
    @JsonProperty("billing_model")
    private BillingModel billingModel;
    @JsonProperty("provider")
    private String provider;
    @JsonProperty("release")
    private String release;
    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("description")
    private String description;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public VnfDescriptor() {
    }

    /**
     * 
     * @param vlinks
     * @param vnfLifecycleEvents
     * @param modifiedAt
     * @param trade
     * @param deploymentFlavours
     * @param provider
     * @param descriptorVersion
     * @param type
     * @param vdu
     * @param version
     * @param id
     * @param description
     * @param createdAt
     * @param name
     * @param providerId
     * @param release
     * @param billingModel
     */
    public VnfDescriptor(Integer providerId, List<Vdu> vdu, String name, String createdAt, String modifiedAt, List<Vlink> vlinks, Boolean trade, String descriptorVersion, List<DeploymentFlavour> deploymentFlavours, String version, List<VnfLifecycleEvent> vnfLifecycleEvents, BillingModel billingModel, String provider, String release, String type, Integer id, String description) {
        this.providerId = providerId;
        this.vdu = vdu;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.vlinks = vlinks;
        this.trade = trade;
        this.descriptorVersion = descriptorVersion;
        this.deploymentFlavours = deploymentFlavours;
        this.version = version;
        this.vnfLifecycleEvents = vnfLifecycleEvents;
        this.billingModel = billingModel;
        this.provider = provider;
        this.release = release;
        this.type = type;
        this.id = id;
        this.description = description;
    }

    /**
     * 
     * @return
     *     The providerId
     */
    @JsonProperty("provider_id")
    public Integer getProviderId() {
        return providerId;
    }

    /**
     * 
     * @param providerId
     *     The provider_id
     */
    @JsonProperty("provider_id")
    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public VnfDescriptor withProviderId(Integer providerId) {
        this.providerId = providerId;
        return this;
    }

    /**
     * 
     * @return
     *     The vdu
     */
    @JsonProperty("vdu")
    public List<Vdu> getVdu() {
        return vdu;
    }

    /**
     * 
     * @param vdu
     *     The vdu
     */
    @JsonProperty("vdu")
    public void setVdu(List<Vdu> vdu) {
        this.vdu = vdu;
    }

    public VnfDescriptor withVdu(List<Vdu> vdu) {
        this.vdu = vdu;
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

    public VnfDescriptor withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The created_at
     */
    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public VnfDescriptor withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * 
     * @return
     *     The modifiedAt
     */
    @JsonProperty("modified_at")
    public String getModifiedAt() {
        return modifiedAt;
    }

    /**
     * 
     * @param modifiedAt
     *     The modified_at
     */
    @JsonProperty("modified_at")
    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public VnfDescriptor withModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    /**
     * 
     * @return
     *     The vlinks
     */
    @JsonProperty("vlinks")
    public List<Vlink> getVlinks() {
        return vlinks;
    }

    /**
     * 
     * @param vlinks
     *     The vlinks
     */
    @JsonProperty("vlinks")
    public void setVlinks(List<Vlink> vlinks) {
        this.vlinks = vlinks;
    }

    public VnfDescriptor withVlinks(List<Vlink> vlinks) {
        this.vlinks = vlinks;
        return this;
    }

    /**
     * 
     * @return
     *     The trade
     */
    @JsonProperty("trade")
    public Boolean getTrade() {
        return trade;
    }

    /**
     * 
     * @param trade
     *     The trade
     */
    @JsonProperty("trade")
    public void setTrade(Boolean trade) {
        this.trade = trade;
    }

    public VnfDescriptor withTrade(Boolean trade) {
        this.trade = trade;
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

    public VnfDescriptor withDescriptorVersion(String descriptorVersion) {
        this.descriptorVersion = descriptorVersion;
        return this;
    }

    /**
     * 
     * @return
     *     The deploymentFlavours
     */
    @JsonProperty("deployment_flavours")
    public List<DeploymentFlavour> getDeploymentFlavours() {
        return deploymentFlavours;
    }

    /**
     * 
     * @param deploymentFlavours
     *     The deployment_flavours
     */
    @JsonProperty("deployment_flavours")
    public void setDeploymentFlavours(List<DeploymentFlavour> deploymentFlavours) {
        this.deploymentFlavours = deploymentFlavours;
    }

    public VnfDescriptor withDeploymentFlavours(List<DeploymentFlavour> deploymentFlavours) {
        this.deploymentFlavours = deploymentFlavours;
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

    public VnfDescriptor withVersion(String version) {
        this.version = version;
        return this;
    }

    /**
     * 
     * @return
     *     The vnfLifecycleEvents
     */
    @JsonProperty("vnf_lifecycle_events")
    public List<VnfLifecycleEvent> getVnfLifecycleEvents() {
        return vnfLifecycleEvents;
    }

    /**
     * 
     * @param vnfLifecycleEvents
     *     The vnf_lifecycle_events
     */
    @JsonProperty("vnf_lifecycle_events")
    public void setVnfLifecycleEvents(List<VnfLifecycleEvent> vnfLifecycleEvents) {
        this.vnfLifecycleEvents = vnfLifecycleEvents;
    }

    public VnfDescriptor withVnfLifecycleEvents(List<VnfLifecycleEvent> vnfLifecycleEvents) {
        this.vnfLifecycleEvents = vnfLifecycleEvents;
        return this;
    }

    /**
     * 
     * @return
     *     The billingModel
     */
    @JsonProperty("billing_model")
    public BillingModel getBillingModel() {
        return billingModel;
    }

    /**
     * 
     * @param billingModel
     *     The billing_model
     */
    @JsonProperty("billing_model")
    public void setBillingModel(BillingModel billingModel) {
        this.billingModel = billingModel;
    }

    public VnfDescriptor withBillingModel(BillingModel billingModel) {
        this.billingModel = billingModel;
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

    public VnfDescriptor withProvider(String provider) {
        this.provider = provider;
        return this;
    }

    /**
     * 
     * @return
     *     The release
     */
    @JsonProperty("release")
    public String getRelease() {
        return release;
    }

    /**
     * 
     * @param release
     *     The release
     */
    @JsonProperty("release")
    public void setRelease(String release) {
        this.release = release;
    }

    public VnfDescriptor withRelease(String release) {
        this.release = release;
        return this;
    }

    /**
     * 
     * @return
     *     The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public VnfDescriptor withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public VnfDescriptor withId(Integer id) {
        this.id = id;
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

    public VnfDescriptor withDescription(String description) {
        this.description = description;
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

    public VnfDescriptor withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(providerId).append(vdu).append(name).append(createdAt).append(modifiedAt).append(vlinks).append(trade).append(descriptorVersion).append(deploymentFlavours).append(version).append(vnfLifecycleEvents).append(billingModel).append(provider).append(release).append(type).append(id).append(description).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VnfDescriptor) == false) {
            return false;
        }
        VnfDescriptor rhs = ((VnfDescriptor) other);
        return new EqualsBuilder().append(providerId, rhs.providerId).append(vdu, rhs.vdu).append(name, rhs.name).append(createdAt, rhs.createdAt).append(modifiedAt, rhs.modifiedAt).append(vlinks, rhs.vlinks).append(trade, rhs.trade).append(descriptorVersion, rhs.descriptorVersion).append(deploymentFlavours, rhs.deploymentFlavours).append(version, rhs.version).append(vnfLifecycleEvents, rhs.vnfLifecycleEvents).append(billingModel, rhs.billingModel).append(provider, rhs.provider).append(release, rhs.release).append(type, rhs.type).append(id, rhs.id).append(description, rhs.description).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
