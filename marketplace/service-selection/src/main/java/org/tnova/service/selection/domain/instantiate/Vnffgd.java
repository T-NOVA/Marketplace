
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
    "vnffgs"
})
public class Vnffgd {

    @JsonProperty("vnffgs")
    private List<Object> vnffgs = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Vnffgd() {
    }

    /**
     * 
     * @param vnffgs
     */
    public Vnffgd(List<Object> vnffgs) {
        this.vnffgs = vnffgs;
    }

    /**
     * 
     * @return
     *     The vnffgs
     */
    @JsonProperty("vnffgs")
    public List<Object> getVnffgs() {
        return vnffgs;
    }

    /**
     * 
     * @param vnffgs
     *     The vnffgs
     */
    @JsonProperty("vnffgs")
    public void setVnffgs(List<Object> vnffgs) {
        this.vnffgs = vnffgs;
    }

    public Vnffgd withVnffgs(List<Object> vnffgs) {
        this.vnffgs = vnffgs;
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

    public Vnffgd withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(vnffgs).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Vnffgd) == false) {
            return false;
        }
        Vnffgd rhs = ((Vnffgd) other);
        return new EqualsBuilder().append(vnffgs, rhs.vnffgs).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
