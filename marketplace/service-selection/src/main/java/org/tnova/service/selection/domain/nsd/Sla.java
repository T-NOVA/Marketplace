
package org.tnova.service.selection.domain.nsd;

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
    "assurance_parameters",
    "billing",
    "constituent_vnf",
    "sla_key"
})
public class Sla {

    @JsonProperty("id")
    private String id;
    @JsonProperty("assurance_parameters")
    private List<AssuranceParameter> assuranceParameters = new ArrayList<AssuranceParameter>();
    @JsonProperty("billing")
    private Billing billing;
    @JsonProperty("constituent_vnf")
    private List<ConstituentVnfSla> constituentVnf = new ArrayList<ConstituentVnfSla>();
    @JsonProperty("sla_key")
    private String slaKey;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Sla() {
    }

    /**
     * 
     * @param slaKey
     * @param id
     * @param billing
     * @param constituentVnf
     * @param assuranceParameters
     */
    public Sla(String id, List<AssuranceParameter> assuranceParameters, Billing billing, List<ConstituentVnfSla> constituentVnf, String slaKey) {
        this.id = id;
        this.assuranceParameters = assuranceParameters;
        this.billing = billing;
        this.constituentVnf = constituentVnf;
        this.slaKey = slaKey;
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

    public Sla withId(String id) {
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

    public Sla withAssuranceParameters(List<AssuranceParameter> assuranceParameters) {
        this.assuranceParameters = assuranceParameters;
        return this;
    }

    /**
     * 
     * @return
     *     The billing
     */
    @JsonProperty("billing")
    public Billing getBilling() {
        return billing;
    }

    /**
     * 
     * @param billing
     *     The billing
     */
    @JsonProperty("billing")
    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public Sla withBilling(Billing billing) {
        this.billing = billing;
        return this;
    }

    /**
     * 
     * @return
     *     The constituentVnf
     */
    @JsonProperty("constituent_vnf")
    public List<ConstituentVnfSla> getConstituentVnf() {
        return constituentVnf;
    }

    /**
     * 
     * @param constituentVnf
     *     The constituent_vnf
     */
    @JsonProperty("constituent_vnf")
    public void setConstituentVnf(List<ConstituentVnfSla> constituentVnf) {
        this.constituentVnf = constituentVnf;
    }

    public Sla withConstituentVnf(List<ConstituentVnfSla> constituentVnf) {
        this.constituentVnf = constituentVnf;
        return this;
    }

    /**
     * 
     * @return
     *     The slaKey
     */
    @JsonProperty("sla_key")
    public String getSlaKey() {
        return slaKey;
    }

    /**
     * 
     * @param slaKey
     *     The sla_key
     */
    @JsonProperty("sla_key")
    public void setSlaKey(String slaKey) {
        this.slaKey = slaKey;
    }

    public Sla withSlaKey(String slaKey) {
        this.slaKey = slaKey;
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

    public Sla withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(assuranceParameters).append(billing).append(constituentVnf).append(slaKey).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Sla) == false) {
            return false;
        }
        Sla rhs = ((Sla) other);
        return new EqualsBuilder().append(id, rhs.id).append(assuranceParameters, rhs.assuranceParameters).append(billing, rhs.billing).append(constituentVnf, rhs.constituentVnf).append(slaKey, rhs.slaKey).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
