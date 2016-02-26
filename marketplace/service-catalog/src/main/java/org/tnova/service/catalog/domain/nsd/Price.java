
/*
 * Copyright 2016  CloudStreet Oy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tnova.service.catalog.domain.nsd;

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
    "price_per_period",
    "setup",
    "unit"
})
public class Price {

    @JsonProperty("price_per_period")
    private long pricePerPeriod;
    @JsonProperty("setup")
    private long setup;
    @JsonProperty("unit")
    private String unit;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Price() {
    }

    /**
     * 
     * @param unit
     * @param setup
     * @param pricePerPeriod
     */
    public Price(long pricePerPeriod, long setup, String unit) {
        this.pricePerPeriod = pricePerPeriod;
        this.setup = setup;
        this.unit = unit;
    }

    /**
     * 
     * @return
     *     The pricePerPeriod
     */
    @JsonProperty("price_per_period")
    public long getPricePerPeriod() {
        return pricePerPeriod;
    }

    /**
     * 
     * @param pricePerPeriod
     *     The price_per_period
     */
    @JsonProperty("price_per_period")
    public void setPricePerPeriod(long pricePerPeriod) {
        this.pricePerPeriod = pricePerPeriod;
    }

    public Price withPricePerPeriod(long pricePerPeriod) {
        this.pricePerPeriod = pricePerPeriod;
        return this;
    }

    /**
     * 
     * @return
     *     The setup
     */
    @JsonProperty("setup")
    public long getSetup() {
        return setup;
    }

    /**
     * 
     * @param setup
     *     The setup
     */
    @JsonProperty("setup")
    public void setSetup(long setup) {
        this.setup = setup;
    }

    public Price withSetup(long setup) {
        this.setup = setup;
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

    public Price withUnit(String unit) {
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

    public Price withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(pricePerPeriod).append(setup).append(unit).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Price) == false) {
            return false;
        }
        Price rhs = ((Price) other);
        return new EqualsBuilder().append(pricePerPeriod, rhs.pricePerPeriod).append(setup, rhs.setup).append(unit, rhs.unit).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
