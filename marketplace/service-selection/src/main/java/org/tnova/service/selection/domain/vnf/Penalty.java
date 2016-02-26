
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
    "type",
    "expression",
    "validity",
    "unit"
})
public class Penalty {

    @JsonProperty("type")
    private String type;
    @JsonProperty("expression")
    private long expression;
    @JsonProperty("validity")
    private String validity;
    @JsonProperty("unit")
    private String unit;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Penalty() {
    }

    /**
     * 
     * @param expression
     * @param unit
     * @param validity
     * @param type
     */
    public Penalty(String type, long expression, String validity, String unit) {
        this.type = type;
        this.expression = expression;
        this.validity = validity;
        this.unit = unit;
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

    public Penalty withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 
     * @return
     *     The expression
     */
    @JsonProperty("expression")
    public long getExpression() {
        return expression;
    }

    /**
     * 
     * @param expression
     *     The expression
     */
    @JsonProperty("expression")
    public void setExpression(long expression) {
        this.expression = expression;
    }

    public Penalty withExpression(long expression) {
        this.expression = expression;
        return this;
    }

    /**
     * 
     * @return
     *     The validity
     */
    @JsonProperty("validity")
    public String getValidity() {
        return validity;
    }

    /**
     * 
     * @param validity
     *     The validity
     */
    @JsonProperty("validity")
    public void setValidity(String validity) {
        this.validity = validity;
    }

    public Penalty withValidity(String validity) {
        this.validity = validity;
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

    public Penalty withUnit(String unit) {
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

    public Penalty withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(type).append(expression).append(validity).append(unit).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Penalty) == false) {
            return false;
        }
        Penalty rhs = ((Penalty) other);
        return new EqualsBuilder().append(type, rhs.type).append(expression, rhs.expression).append(validity, rhs.validity).append(unit, rhs.unit).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
