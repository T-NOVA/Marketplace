
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
    "formula",
    "id",
    "name",
    "penalty",
    "unit",
    "value",
    "violations"
})
public class AssuranceParameter {

    @JsonProperty("formula")
    private String formula;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("penalty")
    private Penalty penalty;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("value")
    private String value;
    @JsonProperty("violations")
    private List<Violation> violations = new ArrayList<Violation>();
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
     * @param violations
     * @param id
     * @param unit
     * @param name
     * @param value
     * @param penalty
     * @param formula
     */
    public AssuranceParameter(String formula, String id, String name, Penalty penalty, String unit, String value, List<Violation> violations) {
        this.formula = formula;
        this.id = id;
        this.name = name;
        this.penalty = penalty;
        this.unit = unit;
        this.value = value;
        this.violations = violations;
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

    public AssuranceParameter withName(String name) {
        this.name = name;
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

    /**
     * 
     * @return
     *     The value
     */
    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    public AssuranceParameter withValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * 
     * @return
     *     The violations
     */
    @JsonProperty("violations")
    public List<Violation> getViolations() {
        return violations;
    }

    /**
     * 
     * @param violations
     *     The violations
     */
    @JsonProperty("violations")
    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }

    public AssuranceParameter withViolations(List<Violation> violations) {
        this.violations = violations;
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
        return new HashCodeBuilder().append(formula).append(id).append(name).append(penalty).append(unit).append(value).append(violations).append(additionalProperties).toHashCode();
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
        return new EqualsBuilder().append(formula, rhs.formula).append(id, rhs.id).append(name, rhs.name).append(penalty, rhs.penalty).append(unit, rhs.unit).append(value, rhs.value).append(violations, rhs.violations).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
