
package org.tnova.service.selection.domain.vnf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.validation.Valid;
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
    "resource_requirements",
    "vm_image",
    "vm_image_format",
    "networking_resources",
    "id",
    "alias",
    "connection_points",
    "monitoring_parameters",
    "vm_image_md5",
    "scale_in_out"
})
public class Vdu {

    @JsonProperty("resource_requirements")
    @Valid
    private ResourceRequirements resourceRequirements;
    @JsonProperty("vm_image")
    private String vmImage;
    @JsonProperty("vm_image_format")
    private String vmImageFormat;
    @JsonProperty("networking_resources")
    private String networkingResources;
    @JsonProperty("id")
    private String id;
    @JsonProperty("alias")
    private String alias;
    @JsonProperty("connection_points")
    @Valid
    private List<ConnectionPoint> connectionPoints = new ArrayList<ConnectionPoint>();
    @JsonProperty("monitoring_parameters")
    @Valid
    private List<MonitoringParameter> monitoringParameters = new ArrayList<MonitoringParameter>();
    @JsonProperty("vm_image_md5")
    private String vmImageMd5;
    @JsonProperty("scale_in_out")
    @Valid
    private ScaleInOut scaleInOut;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Vdu() {
    }

    /**
     * 
     * @param id
     * @param vmImageMd5
     * @param alias
     * @param networkingResources
     * @param monitoringParameters
     * @param connectionPoints
     * @param scaleInOut
     * @param vmImage
     * @param resourceRequirements
     * @param vmImageFormat
     */
    public Vdu(ResourceRequirements resourceRequirements, String vmImage, String vmImageFormat, String networkingResources, String id, String alias, List<ConnectionPoint> connectionPoints, List<MonitoringParameter> monitoringParameters, String vmImageMd5, ScaleInOut scaleInOut) {
        this.resourceRequirements = resourceRequirements;
        this.vmImage = vmImage;
        this.vmImageFormat = vmImageFormat;
        this.networkingResources = networkingResources;
        this.id = id;
        this.alias = alias;
        this.connectionPoints = connectionPoints;
        this.monitoringParameters = monitoringParameters;
        this.vmImageMd5 = vmImageMd5;
        this.scaleInOut = scaleInOut;
    }

    /**
     * 
     * @return
     *     The resourceRequirements
     */
    @JsonProperty("resource_requirements")
    public ResourceRequirements getResourceRequirements() {
        return resourceRequirements;
    }

    /**
     * 
     * @param resourceRequirements
     *     The resource_requirements
     */
    @JsonProperty("resource_requirements")
    public void setResourceRequirements(ResourceRequirements resourceRequirements) {
        this.resourceRequirements = resourceRequirements;
    }

    public Vdu withResourceRequirements(ResourceRequirements resourceRequirements) {
        this.resourceRequirements = resourceRequirements;
        return this;
    }

    /**
     * 
     * @return
     *     The vmImage
     */
    @JsonProperty("vm_image")
    public String getVmImage() {
        return vmImage;
    }

    /**
     * 
     * @param vmImage
     *     The vm_image
     */
    @JsonProperty("vm_image")
    public void setVmImage(String vmImage) {
        this.vmImage = vmImage;
    }

    public Vdu withVmImage(String vmImage) {
        this.vmImage = vmImage;
        return this;
    }

    /**
     * 
     * @return
     *     The vmImageFormat
     */
    @JsonProperty("vm_image_format")
    public String getVmImageFormat() {
        return vmImageFormat;
    }

    /**
     * 
     * @param vmImageFormat
     *     The vm_image_format
     */
    @JsonProperty("vm_image_format")
    public void setVmImageFormat(String vmImageFormat) {
        this.vmImageFormat = vmImageFormat;
    }

    public Vdu withVmImageFormat(String vmImageFormat) {
        this.vmImageFormat = vmImageFormat;
        return this;
    }

    /**
     * 
     * @return
     *     The networkingResources
     */
    @JsonProperty("networking_resources")
    public String getNetworkingResources() {
        return networkingResources;
    }

    /**
     * 
     * @param networkingResources
     *     The networking_resources
     */
    @JsonProperty("networking_resources")
    public void setNetworkingResources(String networkingResources) {
        this.networkingResources = networkingResources;
    }

    public Vdu withNetworkingResources(String networkingResources) {
        this.networkingResources = networkingResources;
        return this;
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

    public Vdu withId(String id) {
        this.id = id;
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

    public Vdu withAlias(String alias) {
        this.alias = alias;
        return this;
    }

    /**
     * 
     * @return
     *     The connectionPoints
     */
    @JsonProperty("connection_points")
    public List<ConnectionPoint> getConnectionPoints() {
        return connectionPoints;
    }

    /**
     * 
     * @param connectionPoints
     *     The connection_points
     */
    @JsonProperty("connection_points")
    public void setConnectionPoints(List<ConnectionPoint> connectionPoints) {
        this.connectionPoints = connectionPoints;
    }

    public Vdu withConnectionPoints(List<ConnectionPoint> connectionPoints) {
        this.connectionPoints = connectionPoints;
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

    public Vdu withMonitoringParameters(List<MonitoringParameter> monitoringParameters) {
        this.monitoringParameters = monitoringParameters;
        return this;
    }

    /**
     * 
     * @return
     *     The vmImageMd5
     */
    @JsonProperty("vm_image_md5")
    public String getVmImageMd5() {
        return vmImageMd5;
    }

    /**
     * 
     * @param vmImageMd5
     *     The vm_image_md5
     */
    @JsonProperty("vm_image_md5")
    public void setVmImageMd5(String vmImageMd5) {
        this.vmImageMd5 = vmImageMd5;
    }

    public Vdu withVmImageMd5(String vmImageMd5) {
        this.vmImageMd5 = vmImageMd5;
        return this;
    }

    /**
     * 
     * @return
     *     The scaleInOut
     */
    @JsonProperty("scale_in_out")
    public ScaleInOut getScaleInOut() {
        return scaleInOut;
    }

    /**
     * 
     * @param scaleInOut
     *     The scale_in_out
     */
    @JsonProperty("scale_in_out")
    public void setScaleInOut(ScaleInOut scaleInOut) {
        this.scaleInOut = scaleInOut;
    }

    public Vdu withScaleInOut(ScaleInOut scaleInOut) {
        this.scaleInOut = scaleInOut;
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

    public Vdu withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(resourceRequirements).append(vmImage).append(vmImageFormat).append(networkingResources).append(id).append(alias).append(connectionPoints).append(monitoringParameters).append(vmImageMd5).append(scaleInOut).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Vdu) == false) {
            return false;
        }
        Vdu rhs = ((Vdu) other);
        return new EqualsBuilder().append(resourceRequirements, rhs.resourceRequirements).append(vmImage, rhs.vmImage).append(vmImageFormat, rhs.vmImageFormat).append(networkingResources, rhs.networkingResources).append(id, rhs.id).append(alias, rhs.alias).append(connectionPoints, rhs.connectionPoints).append(monitoringParameters, rhs.monitoringParameters).append(vmImageMd5, rhs.vmImageMd5).append(scaleInOut, rhs.scaleInOut).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
