
package org.tnova.service.selection.domain.instantiate;

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
    "vnfd_id",
    "vnfi_id",
    "vnfr_id"
})
public class Vnfr {

    @JsonProperty("vnfd_id")
    private String vnfdId;
    @JsonProperty("vnfi_id")
    private List<String> vnfiId = new ArrayList<String>();
    @JsonProperty("vnfr_id")
    private String vnfrId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Vnfr() {
    }

    /**
     * 
     * @param vnfiId
     * @param vnfdId
     * @param vnfrId
     */
    public Vnfr(String vnfdId, List<String> vnfiId, String vnfrId) {
        this.vnfdId = vnfdId;
        this.vnfiId = vnfiId;
        this.vnfrId = vnfrId;
    }

    /**
     * 
     * @return
     *     The vnfdId
     */
    @JsonProperty("vnfd_id")
    public String getVnfdId() {
        return vnfdId;
    }

    /**
     * 
     * @param vnfdId
     *     The vnfd_id
     */
    @JsonProperty("vnfd_id")
    public void setVnfdId(String vnfdId) {
        this.vnfdId = vnfdId;
    }

    public Vnfr withVnfdId(String vnfdId) {
        this.vnfdId = vnfdId;
        return this;
    }

    /**
     * 
     * @return
     *     The vnfiId
     */
    @JsonProperty("vnfi_id")
    public List<String> getVnfiId() {
        return vnfiId;
    }

    /**
     * 
     * @param vnfiId
     *     The vnfi_id
     */
    @JsonProperty("vnfi_id")
    public void setVnfiId(List<String> vnfiId) {
        this.vnfiId = vnfiId;
    }

    public Vnfr withVnfiId(List<String> vnfiId) {
        this.vnfiId = vnfiId;
        return this;
    }

    /**
     * 
     * @return
     *     The vnfrId
     */
    @JsonProperty("vnfr_id")
    public String getVnfrId() {
        return vnfrId;
    }

    /**
     * 
     * @param vnfrId
     *     The vnfr_id
     */
    @JsonProperty("vnfr_id")
    public void setVnfrId(String vnfrId) {
        this.vnfrId = vnfrId;
    }

    public Vnfr withVnfrId(String vnfrId) {
        this.vnfrId = vnfrId;
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

    public Vnfr withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(vnfdId).append(vnfiId).append(vnfrId).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Vnfr) == false) {
            return false;
        }
        Vnfr rhs = ((Vnfr) other);
        return new EqualsBuilder().append(vnfdId, rhs.vnfdId).append(vnfiId, rhs.vnfiId).append(vnfrId, rhs.vnfrId).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
