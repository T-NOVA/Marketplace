
package org.tnova.service.selection.domain.vnf;

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
    "network_interface_bandwidth_unit",
    "hypervisor_parameters",
    "memory_unit",
    "network_interface_card_capabilities",
    "storage",
    "network_interface_bandwidth",
    "platform_pcie_parameters",
    "vcpus",
    "vswitch_capabilities",
    "data_processing_acceleration_library",
    "memory",
    "memory_parameters",
    "cpu_support_accelerator"
})
public class ResourceRequirements {

    @JsonProperty("network_interface_bandwidth_unit")
    private String networkInterfaceBandwidthUnit;
    @JsonProperty("hypervisor_parameters")
    private HypervisorParameters hypervisorParameters;
    @JsonProperty("memory_unit")
    private String memoryUnit;
    @JsonProperty("network_interface_card_capabilities")
    private NetworkInterfaceCardCapabilities networkInterfaceCardCapabilities;
    @JsonProperty("storage")
    private Storage storage;
    @JsonProperty("network_interface_bandwidth")
    private String networkInterfaceBandwidth;
    @JsonProperty("platform_pcie_parameters")
    private PlatformPcieParameters platformPcieParameters;
    @JsonProperty("vcpus")
    private Integer vcpus;
    @JsonProperty("vswitch_capabilities")
    private VswitchCapabilities vswitchCapabilities;
    @JsonProperty("data_processing_acceleration_library")
    private String dataProcessingAccelerationLibrary;
    @JsonProperty("memory")
    private Integer memory;
    @JsonProperty("memory_parameters")
    private MemoryParameters memoryParameters;
    @JsonProperty("cpu_support_accelerator")
    private String cpuSupportAccelerator;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResourceRequirements() {
    }

    /**
     * 
     * @param hypervisorParameters
     * @param cpuSupportAccelerator
     * @param networkInterfaceCardCapabilities
     * @param dataProcessingAccelerationLibrary
     * @param networkInterfaceBandwidthUnit
     * @param networkInterfaceBandwidth
     * @param vcpus
     * @param memoryUnit
     * @param platformPcieParameters
     * @param memory
     * @param vswitchCapabilities
     * @param storage
     * @param memoryParameters
     */
    public ResourceRequirements(String networkInterfaceBandwidthUnit, HypervisorParameters hypervisorParameters, String memoryUnit, NetworkInterfaceCardCapabilities networkInterfaceCardCapabilities, Storage storage, String networkInterfaceBandwidth, PlatformPcieParameters platformPcieParameters, Integer vcpus, VswitchCapabilities vswitchCapabilities, String dataProcessingAccelerationLibrary, Integer memory, MemoryParameters memoryParameters, String cpuSupportAccelerator) {
        this.networkInterfaceBandwidthUnit = networkInterfaceBandwidthUnit;
        this.hypervisorParameters = hypervisorParameters;
        this.memoryUnit = memoryUnit;
        this.networkInterfaceCardCapabilities = networkInterfaceCardCapabilities;
        this.storage = storage;
        this.networkInterfaceBandwidth = networkInterfaceBandwidth;
        this.platformPcieParameters = platformPcieParameters;
        this.vcpus = vcpus;
        this.vswitchCapabilities = vswitchCapabilities;
        this.dataProcessingAccelerationLibrary = dataProcessingAccelerationLibrary;
        this.memory = memory;
        this.memoryParameters = memoryParameters;
        this.cpuSupportAccelerator = cpuSupportAccelerator;
    }

    /**
     * 
     * @return
     *     The networkInterfaceBandwidthUnit
     */
    @JsonProperty("network_interface_bandwidth_unit")
    public String getNetworkInterfaceBandwidthUnit() {
        return networkInterfaceBandwidthUnit;
    }

    /**
     * 
     * @param networkInterfaceBandwidthUnit
     *     The network_interface_bandwidth_unit
     */
    @JsonProperty("network_interface_bandwidth_unit")
    public void setNetworkInterfaceBandwidthUnit(String networkInterfaceBandwidthUnit) {
        this.networkInterfaceBandwidthUnit = networkInterfaceBandwidthUnit;
    }

    public ResourceRequirements withNetworkInterfaceBandwidthUnit(String networkInterfaceBandwidthUnit) {
        this.networkInterfaceBandwidthUnit = networkInterfaceBandwidthUnit;
        return this;
    }

    /**
     * 
     * @return
     *     The hypervisorParameters
     */
    @JsonProperty("hypervisor_parameters")
    public HypervisorParameters getHypervisorParameters() {
        return hypervisorParameters;
    }

    /**
     * 
     * @param hypervisorParameters
     *     The hypervisor_parameters
     */
    @JsonProperty("hypervisor_parameters")
    public void setHypervisorParameters(HypervisorParameters hypervisorParameters) {
        this.hypervisorParameters = hypervisorParameters;
    }

    public ResourceRequirements withHypervisorParameters(HypervisorParameters hypervisorParameters) {
        this.hypervisorParameters = hypervisorParameters;
        return this;
    }

    /**
     * 
     * @return
     *     The memoryUnit
     */
    @JsonProperty("memory_unit")
    public String getMemoryUnit() {
        return memoryUnit;
    }

    /**
     * 
     * @param memoryUnit
     *     The memory_unit
     */
    @JsonProperty("memory_unit")
    public void setMemoryUnit(String memoryUnit) {
        this.memoryUnit = memoryUnit;
    }

    public ResourceRequirements withMemoryUnit(String memoryUnit) {
        this.memoryUnit = memoryUnit;
        return this;
    }

    /**
     * 
     * @return
     *     The networkInterfaceCardCapabilities
     */
    @JsonProperty("network_interface_card_capabilities")
    public NetworkInterfaceCardCapabilities getNetworkInterfaceCardCapabilities() {
        return networkInterfaceCardCapabilities;
    }

    /**
     * 
     * @param networkInterfaceCardCapabilities
     *     The network_interface_card_capabilities
     */
    @JsonProperty("network_interface_card_capabilities")
    public void setNetworkInterfaceCardCapabilities(NetworkInterfaceCardCapabilities networkInterfaceCardCapabilities) {
        this.networkInterfaceCardCapabilities = networkInterfaceCardCapabilities;
    }

    public ResourceRequirements withNetworkInterfaceCardCapabilities(NetworkInterfaceCardCapabilities networkInterfaceCardCapabilities) {
        this.networkInterfaceCardCapabilities = networkInterfaceCardCapabilities;
        return this;
    }

    /**
     * 
     * @return
     *     The storage
     */
    @JsonProperty("storage")
    public Storage getStorage() {
        return storage;
    }

    /**
     * 
     * @param storage
     *     The storage
     */
    @JsonProperty("storage")
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public ResourceRequirements withStorage(Storage storage) {
        this.storage = storage;
        return this;
    }

    /**
     * 
     * @return
     *     The networkInterfaceBandwidth
     */
    @JsonProperty("network_interface_bandwidth")
    public String getNetworkInterfaceBandwidth() {
        return networkInterfaceBandwidth;
    }

    /**
     * 
     * @param networkInterfaceBandwidth
     *     The network_interface_bandwidth
     */
    @JsonProperty("network_interface_bandwidth")
    public void setNetworkInterfaceBandwidth(String networkInterfaceBandwidth) {
        this.networkInterfaceBandwidth = networkInterfaceBandwidth;
    }

    public ResourceRequirements withNetworkInterfaceBandwidth(String networkInterfaceBandwidth) {
        this.networkInterfaceBandwidth = networkInterfaceBandwidth;
        return this;
    }

    /**
     * 
     * @return
     *     The platformPcieParameters
     */
    @JsonProperty("platform_pcie_parameters")
    public PlatformPcieParameters getPlatformPcieParameters() {
        return platformPcieParameters;
    }

    /**
     * 
     * @param platformPcieParameters
     *     The platform_pcie_parameters
     */
    @JsonProperty("platform_pcie_parameters")
    public void setPlatformPcieParameters(PlatformPcieParameters platformPcieParameters) {
        this.platformPcieParameters = platformPcieParameters;
    }

    public ResourceRequirements withPlatformPcieParameters(PlatformPcieParameters platformPcieParameters) {
        this.platformPcieParameters = platformPcieParameters;
        return this;
    }

    /**
     * 
     * @return
     *     The vcpus
     */
    @JsonProperty("vcpus")
    public Integer getVcpus() {
        return vcpus;
    }

    /**
     * 
     * @param vcpus
     *     The vcpus
     */
    @JsonProperty("vcpus")
    public void setVcpus(Integer vcpus) {
        this.vcpus = vcpus;
    }

    public ResourceRequirements withVcpus(Integer vcpus) {
        this.vcpus = vcpus;
        return this;
    }

    /**
     * 
     * @return
     *     The vswitchCapabilities
     */
    @JsonProperty("vswitch_capabilities")
    public VswitchCapabilities getVswitchCapabilities() {
        return vswitchCapabilities;
    }

    /**
     * 
     * @param vswitchCapabilities
     *     The vswitch_capabilities
     */
    @JsonProperty("vswitch_capabilities")
    public void setVswitchCapabilities(VswitchCapabilities vswitchCapabilities) {
        this.vswitchCapabilities = vswitchCapabilities;
    }

    public ResourceRequirements withVswitchCapabilities(VswitchCapabilities vswitchCapabilities) {
        this.vswitchCapabilities = vswitchCapabilities;
        return this;
    }

    /**
     * 
     * @return
     *     The dataProcessingAccelerationLibrary
     */
    @JsonProperty("data_processing_acceleration_library")
    public String getDataProcessingAccelerationLibrary() {
        return dataProcessingAccelerationLibrary;
    }

    /**
     * 
     * @param dataProcessingAccelerationLibrary
     *     The data_processing_acceleration_library
     */
    @JsonProperty("data_processing_acceleration_library")
    public void setDataProcessingAccelerationLibrary(String dataProcessingAccelerationLibrary) {
        this.dataProcessingAccelerationLibrary = dataProcessingAccelerationLibrary;
    }

    public ResourceRequirements withDataProcessingAccelerationLibrary(String dataProcessingAccelerationLibrary) {
        this.dataProcessingAccelerationLibrary = dataProcessingAccelerationLibrary;
        return this;
    }

    /**
     * 
     * @return
     *     The memory
     */
    @JsonProperty("memory")
    public Integer getMemory() {
        return memory;
    }

    /**
     * 
     * @param memory
     *     The memory
     */
    @JsonProperty("memory")
    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public ResourceRequirements withMemory(Integer memory) {
        this.memory = memory;
        return this;
    }

    /**
     * 
     * @return
     *     The memoryParameters
     */
    @JsonProperty("memory_parameters")
    public MemoryParameters getMemoryParameters() {
        return memoryParameters;
    }

    /**
     * 
     * @param memoryParameters
     *     The memory_parameters
     */
    @JsonProperty("memory_parameters")
    public void setMemoryParameters(MemoryParameters memoryParameters) {
        this.memoryParameters = memoryParameters;
    }

    public ResourceRequirements withMemoryParameters(MemoryParameters memoryParameters) {
        this.memoryParameters = memoryParameters;
        return this;
    }

    /**
     * 
     * @return
     *     The cpuSupportAccelerator
     */
    @JsonProperty("cpu_support_accelerator")
    public String getCpuSupportAccelerator() {
        return cpuSupportAccelerator;
    }

    /**
     * 
     * @param cpuSupportAccelerator
     *     The cpu_support_accelerator
     */
    @JsonProperty("cpu_support_accelerator")
    public void setCpuSupportAccelerator(String cpuSupportAccelerator) {
        this.cpuSupportAccelerator = cpuSupportAccelerator;
    }

    public ResourceRequirements withCpuSupportAccelerator(String cpuSupportAccelerator) {
        this.cpuSupportAccelerator = cpuSupportAccelerator;
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

    public ResourceRequirements withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(networkInterfaceBandwidthUnit).append(hypervisorParameters).append(memoryUnit).append(networkInterfaceCardCapabilities).append(storage).append(networkInterfaceBandwidth).append(platformPcieParameters).append(vcpus).append(vswitchCapabilities).append(dataProcessingAccelerationLibrary).append(memory).append(memoryParameters).append(cpuSupportAccelerator).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ResourceRequirements) == false) {
            return false;
        }
        ResourceRequirements rhs = ((ResourceRequirements) other);
        return new EqualsBuilder().append(networkInterfaceBandwidthUnit, rhs.networkInterfaceBandwidthUnit).append(hypervisorParameters, rhs.hypervisorParameters).append(memoryUnit, rhs.memoryUnit).append(networkInterfaceCardCapabilities, rhs.networkInterfaceCardCapabilities).append(storage, rhs.storage).append(networkInterfaceBandwidth, rhs.networkInterfaceBandwidth).append(platformPcieParameters, rhs.platformPcieParameters).append(vcpus, rhs.vcpus).append(vswitchCapabilities, rhs.vswitchCapabilities).append(dataProcessingAccelerationLibrary, rhs.dataProcessingAccelerationLibrary).append(memory, rhs.memory).append(memoryParameters, rhs.memoryParameters).append(cpuSupportAccelerator, rhs.cpuSupportAccelerator).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
