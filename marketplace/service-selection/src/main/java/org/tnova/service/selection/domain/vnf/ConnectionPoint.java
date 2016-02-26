
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
    "vlink_ref",
    "id"
})
public class ConnectionPoint {

    @JsonProperty("vlink_ref")
    private String vlinkRef;
    @JsonProperty("id")
    private String id;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ConnectionPoint() {
    }

    /**
     * 
     * @param id
     * @param vlinkRef
     */
    public ConnectionPoint(String vlinkRef, String id) {
        this.vlinkRef = vlinkRef;
        this.id = id;
    }

    /**
     * 
     * @return
     *     The vlinkRef
     */
    @JsonProperty("vlink_ref")
    public String getVlinkRef() {
        return vlinkRef;
    }

    /**
     * 
     * @param vlinkRef
     *     The vlink_ref
     */
    @JsonProperty("vlink_ref")
    public void setVlinkRef(String vlinkRef) {
        this.vlinkRef = vlinkRef;
    }

    public ConnectionPoint withVlinkRef(String vlinkRef) {
        this.vlinkRef = vlinkRef;
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

    public ConnectionPoint withId(String id) {
        this.id = id;
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

    public ConnectionPoint withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(vlinkRef).append(id).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ConnectionPoint) == false) {
            return false;
        }
        ConnectionPoint rhs = ((ConnectionPoint) other);
        return new EqualsBuilder().append(vlinkRef, rhs.vlinkRef).append(id, rhs.id).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
