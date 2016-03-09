
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
    "price",
    "model",
    "period"
})
public class BillingModel {

    @JsonProperty("price")
    private Price price;
    @JsonProperty("model")
    private String model;
    @JsonProperty("period")
    private String period;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public BillingModel() {
    }

    /**
     * 
     * @param model
     * @param price
     * @param period
     */
    public BillingModel(Price price, String model, String period) {
        this.price = price;
        this.model = model;
        this.period = period;
    }

    /**
     * 
     * @return
     *     The price
     */
    @JsonProperty("price")
    public Price getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *     The price
     */
    @JsonProperty("price")
    public void setPrice(Price price) {
        this.price = price;
    }

    public BillingModel withPrice(Price price) {
        this.price = price;
        return this;
    }

    /**
     * 
     * @return
     *     The model
     */
    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    /**
     * 
     * @param model
     *     The model
     */
    @JsonProperty("model")
    public void setModel(String model) {
        this.model = model;
    }

    public BillingModel withModel(String model) {
        this.model = model;
        return this;
    }

    /**
     * 
     * @return
     *     The period
     */
    @JsonProperty("period")
    public String getPeriod() {
        return period;
    }

    /**
     * 
     * @param period
     *     The period
     */
    @JsonProperty("period")
    public void setPeriod(String period) {
        this.period = period;
    }

    public BillingModel withPeriod(String period) {
        this.period = period;
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

    public BillingModel withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(price).append(model).append(period).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BillingModel) == false) {
            return false;
        }
        BillingModel rhs = ((BillingModel) other);
        return new EqualsBuilder().append(price, rhs.price).append(model, rhs.model).append(period, rhs.period).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
