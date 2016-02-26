package org.tnova.service.selection.domain.nsd;

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
    "connection_point_id",
    "type"
})
public class ConnectionPoint {

    @JsonProperty("connection_point_id")
    private String connectionPointId;
    @JsonProperty("type")
    private String type;
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
     * @param type
     * @param connectionPointId
     */
    public ConnectionPoint(String connectionPointId, String type) {
        this.connectionPointId = connectionPointId;
        this.type = type;
    }

    /**
     * 
     * @return
     *     The connectionPointId
     */
    @JsonProperty("connection_point_id")
    public String getConnectionPointId() {
        return connectionPointId;
    }

    /**
     * 
     * @param connectionPointId
     *     The connection_point_id
     */
    @JsonProperty("connection_point_id")
    public void setConnectionPointId(String connectionPointId) {
        this.connectionPointId = connectionPointId;
    }

    public ConnectionPoint withConnectionPointId(String connectionPointId) {
        this.connectionPointId = connectionPointId;
        return this;
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

    public ConnectionPoint withType(String type) {
        this.type = type;
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
        return new HashCodeBuilder().append(connectionPointId).append(type).append(additionalProperties).toHashCode();
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
        return new EqualsBuilder().append(connectionPointId, rhs.connectionPointId).append(type, rhs.type).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
