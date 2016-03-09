
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
    "size_unit",
    "persistence",
    "size"
})
public class Storage {

    @JsonProperty("size_unit")
    private String sizeUnit;
    @JsonProperty("persistence")
    private Boolean persistence;
    @JsonProperty("size")
    private Integer size;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Storage() {
    }

    /**
     * 
     * @param persistence
     * @param sizeUnit
     * @param size
     */
    public Storage(String sizeUnit, Boolean persistence, Integer size) {
        this.sizeUnit = sizeUnit;
        this.persistence = persistence;
        this.size = size;
    }

    /**
     * 
     * @return
     *     The sizeUnit
     */
    @JsonProperty("size_unit")
    public String getSizeUnit() {
        return sizeUnit;
    }

    /**
     * 
     * @param sizeUnit
     *     The size_unit
     */
    @JsonProperty("size_unit")
    public void setSizeUnit(String sizeUnit) {
        this.sizeUnit = sizeUnit;
    }

    public Storage withSizeUnit(String sizeUnit) {
        this.sizeUnit = sizeUnit;
        return this;
    }

    /**
     * 
     * @return
     *     The persistence
     */
    @JsonProperty("persistence")
    public Boolean getPersistence() {
        return persistence;
    }

    /**
     * 
     * @param persistence
     *     The persistence
     */
    @JsonProperty("persistence")
    public void setPersistence(Boolean persistence) {
        this.persistence = persistence;
    }

    public Storage withPersistence(Boolean persistence) {
        this.persistence = persistence;
        return this;
    }

    /**
     * 
     * @return
     *     The size
     */
    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    /**
     * 
     * @param size
     *     The size
     */
    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    public Storage withSize(Integer size) {
        this.size = size;
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

    public Storage withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(sizeUnit).append(persistence).append(size).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Storage) == false) {
            return false;
        }
        Storage rhs = ((Storage) other);
        return new EqualsBuilder().append(sizeUnit, rhs.sizeUnit).append(persistence, rhs.persistence).append(size, rhs.size).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
