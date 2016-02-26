
package org.tnova.service.selection.domain;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "vnfi_id",
    "vnfd_id"
})
public class Vnf {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("vnfi_id")
    private String vnfiId;
    @JsonProperty("vnfd_id")
    private String vnfdId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Vnf() {
    }

    public Vnf(Integer id, String vnfiId, String vnfdId) {
        this.id = id;
        this.vnfiId = vnfiId;
        this.vnfdId = vnfdId;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Vnf withId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonProperty("vnfi_id")
    public String getVnfiId() {
        return vnfiId;
    }

    @JsonProperty("vnfi_id")
    public void setVnfiId(String vnfiId) {
        this.vnfiId = vnfiId;
    }

    public Vnf withVnfiId(String vnfiId) {
        this.vnfiId = vnfiId;
        return this;
    }

    @JsonProperty("vnfd_id")
    public String getVnfdId() {
        return vnfdId;
    }

    @JsonProperty("vnfd_id")
    public void setVnfdId(String vnfdId) {
        this.vnfdId = vnfdId;
    }

    public Vnf withVnfdId(String vnfdId) {
        this.vnfdId = vnfdId;
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

    public Vnf withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(vnfiId).append(vnfdId).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Vnf) == false) {
            return false;
        }
        Vnf rhs = ((Vnf) other);

        return new EqualsBuilder()
            .append(id, rhs.id)
            .append(vnfiId, rhs.vnfiId)
            .append(vnfdId, rhs.vnfdId)
            .append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
