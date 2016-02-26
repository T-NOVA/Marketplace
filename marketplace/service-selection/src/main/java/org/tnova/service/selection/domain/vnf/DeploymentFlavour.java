
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
@JsonPropertyOrder({
    "vdu_reference",
    "constraint",
    "flavour_key",
    "vlink_reference",
    "id",
    "assurance_parameters"
})
public class DeploymentFlavour {

    @JsonProperty("vdu_reference")
    @Valid
    private List<String> vduReference = new ArrayList<String>();
    @JsonProperty("constraint")
    private String constraint;
    @JsonProperty("flavour_key")
    private String flavourKey;
    @JsonProperty("vlink_reference")
    @Valid
    private List<String> vlinkReference = new ArrayList<String>();

    @JsonProperty("id")
    private String id;

    @JsonProperty("assurance_parameters")
    @Valid
    private List<AssuranceParameter> assuranceParameters = new ArrayList<AssuranceParameter>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public DeploymentFlavour() {
    }

    /**
     * 
     * @param id
     * @param vlinkReference
     * @param flavourKey
     * @param assuranceParameters
     * @param constraint
     * @param vduReference
     */
    public DeploymentFlavour(List<String> vduReference, String constraint, String flavourKey, List<String> vlinkReference, String id, List<AssuranceParameter> assuranceParameters) {
        this.vduReference = vduReference;
        this.constraint = constraint;
        this.flavourKey = flavourKey;
        this.vlinkReference = vlinkReference;
        this.id = id;
        this.assuranceParameters = assuranceParameters;
    }

    /**
     * 
     * @return
     *     The vduReference
     */
    @JsonProperty("vdu_reference")
    public List<String> getVduReference() {
        return vduReference;
    }

    /**
     * 
     * @param vduReference
     *     The vdu_reference
     */
    @JsonProperty("vdu_reference")
    public void setVduReference(List<String> vduReference) {
        this.vduReference = vduReference;
    }

    public DeploymentFlavour withVduReference(List<String> vduReference) {
        this.vduReference = vduReference;
        return this;
    }

    /**
     * 
     * @return
     *     The constraint
     */
    @JsonProperty("constraint")
    public String getConstraint() {
        return constraint;
    }

    /**
     * 
     * @param constraint
     *     The constraint
     */
    @JsonProperty("constraint")
    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public DeploymentFlavour withConstraint(String constraint) {
        this.constraint = constraint;
        return this;
    }

    /**
     * 
     * @return
     *     The flavourKey
     */
    @JsonProperty("flavour_key")
    public String getFlavourKey() {
        return flavourKey;
    }

    /**
     * 
     * @param flavourKey
     *     The flavour_key
     */
    @JsonProperty("flavour_key")
    public void setFlavourKey(String flavourKey) {
        this.flavourKey = flavourKey;
    }

    public DeploymentFlavour withFlavourKey(String flavourKey) {
        this.flavourKey = flavourKey;
        return this;
    }

    /**
     * 
     * @return
     *     The vlinkReference
     */
    @JsonProperty("vlink_reference")
    public List<String> getVlinkReference() {
        return vlinkReference;
    }

    /**
     * 
     * @param vlinkReference
     *     The vlink_reference
     */
    @JsonProperty("vlink_reference")
    public void setVlinkReference(List<String> vlinkReference) {
        this.vlinkReference = vlinkReference;
    }

    public DeploymentFlavour withVlinkReference(List<String> vlinkReference) {
        this.vlinkReference = vlinkReference;
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

    public DeploymentFlavour withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The assuranceParameters
     */
    @JsonProperty("assurance_parameters")
    public List<AssuranceParameter> getAssuranceParameters() {
        return assuranceParameters;
    }

    /**
     * 
     * @param assuranceParameters
     *     The assurance_parameters
     */
    @JsonProperty("assurance_parameters")
    public void setAssuranceParameters(List<AssuranceParameter> assuranceParameters) {
        this.assuranceParameters = assuranceParameters;
    }

    public DeploymentFlavour withAssuranceParameters(List<AssuranceParameter> assuranceParameters) {
        this.assuranceParameters = assuranceParameters;
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

    public DeploymentFlavour withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(vduReference).append(constraint).append(flavourKey).append(vlinkReference).append(id).append(assuranceParameters).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DeploymentFlavour) == false) {
            return false;
        }
        DeploymentFlavour rhs = ((DeploymentFlavour) other);
        return new EqualsBuilder().append(vduReference, rhs.vduReference).append(constraint, rhs.constraint).append(flavourKey, rhs.flavourKey).append(vlinkReference, rhs.vlinkReference).append(id, rhs.id).append(assuranceParameters, rhs.assuranceParameters).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
