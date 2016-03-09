
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
    "violation",
    "value",
    "penalty",
    "formula",
    "rel_id",
    "id",
    "unit"
})
public class AssuranceParameter {

    @JsonProperty("violation")
    private List<Violation> violation = new ArrayList<Violation>();
    @JsonProperty("value")
    private Integer value;
    @JsonProperty("penalty")
    private Penalty penalty;
    @JsonProperty("formula")
    private String formula;
    @JsonProperty("rel_id")
    private String relId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("unit")
    private String unit;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public AssuranceParameter() {
    }

    /**
     * 
     * @param id
     * @param unit
     * @param violation
     * @param relId
     * @param value
     * @param penalty
     * @param formula
     */
    public AssuranceParameter(List<Violation> violation, Integer value, Penalty penalty, String formula, String relId, String id, String unit) {
        this.violation = violation;
        this.value = value;
        this.penalty = penalty;
        this.formula = formula;
        this.relId = relId;
        this.id = id;
        this.unit = unit;
    }

    /**
     * 
     * @return
     *     The violation
     */
    @JsonProperty("violation")
    public List<Violation> getViolation() {
        return violation;
    }

    /**
     * 
     * @param violation
     *     The violation
     */
    @JsonProperty("violation")
    public void setViolation(List<Violation> violation) {
        this.violation = violation;
    }

    public AssuranceParameter withViolation(List<Violation> violation) {
        this.violation = violation;
        return this;
    }

    /**
     * 
     * @return
     *     The value
     */
    @JsonProperty("value")
    public Integer getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    @JsonProperty("value")
    public void setValue(Integer value) {
        this.value = value;
    }

    public AssuranceParameter withValue(Integer value) {
        this.value = value;
        return this;
    }

    /**
     * 
     * @return
     *     The penalty
     */
    @JsonProperty("penalty")
    public Penalty getPenalty() {
        return penalty;
    }

    /**
     * 
     * @param penalty
     *     The penalty
     */
    @JsonProperty("penalty")
    public void setPenalty(Penalty penalty) {
        this.penalty = penalty;
    }

    public AssuranceParameter withPenalty(Penalty penalty) {
        this.penalty = penalty;
        return this;
    }

    /**
     * 
     * @return
     *     The formula
     */
    @JsonProperty("formula")
    public String getFormula() {
        return formula;
    }

    /**
     * 
     * @param formula
     *     The formula
     */
    @JsonProperty("formula")
    public void setFormula(String formula) {
        this.formula = formula;
    }

    public AssuranceParameter withFormula(String formula) {
        this.formula = formula;
        return this;
    }

    /**
     * 
     * @return
     *     The relId
     */
    @JsonProperty("rel_id")
    public String getRelId() {
        return relId;
    }

    /**
     * 
     * @param relId
     *     The rel_id
     */
    @JsonProperty("rel_id")
    public void setRelId(String relId) {
        this.relId = relId;
    }

    public AssuranceParameter withRelId(String relId) {
        this.relId = relId;
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

    public AssuranceParameter withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The unit
     */
    @JsonProperty("unit")
    public String getUnit() {
        return unit;
    }

    /**
     * 
     * @param unit
     *     The unit
     */
    @JsonProperty("unit")
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public AssuranceParameter withUnit(String unit) {
        this.unit = unit;
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

    public AssuranceParameter withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(violation).append(value).append(penalty).append(formula).append(relId).append(id).append(unit).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AssuranceParameter) == false) {
            return false;
        }
        AssuranceParameter rhs = ((AssuranceParameter) other);
        return new EqualsBuilder().append(violation, rhs.violation).append(value, rhs.value).append(penalty, rhs.penalty).append(formula, rhs.formula).append(relId, rhs.relId).append(id, rhs.id).append(unit, rhs.unit).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
