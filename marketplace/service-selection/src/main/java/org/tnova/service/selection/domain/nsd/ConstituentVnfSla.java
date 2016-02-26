
package org.tnova.service.selection.domain.nsd;

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
    "number_of_instances",
    "redundancy_model",
    "vnf_flavour_id_reference",
    "vnf_reference"
})
public class ConstituentVnfSla
{

    @JsonProperty("number_of_instances")
    private long numberOfInstances;
    @JsonProperty("redundancy_model")
    private String redundancyModel;
    @JsonProperty("vnf_flavour_id_reference")
    private String vnfFlavourIdReference;
    @JsonProperty("vnf_reference")
    private String vnfReference;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ConstituentVnfSla() {
    }

    /**
     * 
     * @param numberOfInstances
     * @param redundancyModel
     * @param vnfReference
     * @param vnfFlavourIdReference
     */
    public ConstituentVnfSla( long numberOfInstances, String redundancyModel, String vnfFlavourIdReference,
        String vnfReference ) {
        this.numberOfInstances = numberOfInstances;
        this.redundancyModel = redundancyModel;
        this.vnfFlavourIdReference = vnfFlavourIdReference;
        this.vnfReference = vnfReference;
    }

    /**
     * 
     * @return
     *     The numberOfInstances
     */
    @JsonProperty("number_of_instances")
    public long getNumberOfInstances() {
        return numberOfInstances;
    }

    /**
     * 
     * @param numberOfInstances
     *     The number_of_instances
     */
    @JsonProperty("number_of_instances")
    public void setNumberOfInstances(long numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public ConstituentVnfSla withNumberOfInstances(long numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
        return this;
    }

    /**
     * 
     * @return
     *     The redundancyModel
     */
    @JsonProperty("redundancy_model")
    public String getRedundancyModel() {
        return redundancyModel;
    }

    /**
     * 
     * @param redundancyModel
     *     The redundancy_model
     */
    @JsonProperty("redundancy_model")
    public void setRedundancyModel(String redundancyModel) {
        this.redundancyModel = redundancyModel;
    }

    public ConstituentVnfSla withRedundancyModel(String redundancyModel) {
        this.redundancyModel = redundancyModel;
        return this;
    }

    /**
     * 
     * @return
     *     The vnfFlavourIdReference
     */
    @JsonProperty("vnf_flavour_id_reference")
    public String getVnfFlavourIdReference() {
        return vnfFlavourIdReference;
    }

    /**
     * 
     * @param vnfFlavourIdReference
     *     The vnf_flavour_id_reference
     */
    @JsonProperty("vnf_flavour_id_reference")
    public void setVnfFlavourIdReference(String vnfFlavourIdReference) {
        this.vnfFlavourIdReference = vnfFlavourIdReference;
    }

    public ConstituentVnfSla withVnfFlavourIdReference(String vnfFlavourIdReference) {
        this.vnfFlavourIdReference = vnfFlavourIdReference;
        return this;
    }

    /**
     * 
     * @return
     *     The vnfReference
     */
    @JsonProperty("vnf_reference")
    public String getVnfReference() {
        return vnfReference;
    }

    /**
     * 
     * @param vnfReference
     *     The vnf_reference
     */
    @JsonProperty("vnf_reference")
    public void setVnfReference(String vnfReference) {
        this.vnfReference = vnfReference;
    }

    public ConstituentVnfSla withVnfReference(String vnfReference) {
        this.vnfReference = vnfReference;
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

    public ConstituentVnfSla withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(numberOfInstances).append(redundancyModel).append(vnfFlavourIdReference).append(vnfReference).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ConstituentVnfSla ) == false) {
            return false;
        }
        ConstituentVnfSla rhs = ((ConstituentVnfSla) other);
        return new EqualsBuilder().append(numberOfInstances, rhs.numberOfInstances).append(redundancyModel, rhs.redundancyModel).append(vnfFlavourIdReference, rhs.vnfFlavourIdReference).append(vnfReference, rhs.vnfReference).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
