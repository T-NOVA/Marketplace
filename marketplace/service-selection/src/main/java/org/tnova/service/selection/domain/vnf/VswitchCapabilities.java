
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
    "version",
    "type",
    "overlay_tunnel"
})
public class VswitchCapabilities {

    @JsonProperty("version")
    private String version;
    @JsonProperty("type")
    private String type;
    @JsonProperty("overlay_tunnel")
    private String overlayTunnel;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public VswitchCapabilities() {
    }

    /**
     * 
     * @param type
     * @param overlayTunnel
     * @param version
     */
    public VswitchCapabilities(String version, String type, String overlayTunnel) {
        this.version = version;
        this.type = type;
        this.overlayTunnel = overlayTunnel;
    }

    /**
     * 
     * @return
     *     The version
     */
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    public VswitchCapabilities withVersion(String version) {
        this.version = version;
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

    public VswitchCapabilities withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 
     * @return
     *     The overlayTunnel
     */
    @JsonProperty("overlay_tunnel")
    public String getOverlayTunnel() {
        return overlayTunnel;
    }

    /**
     * 
     * @param overlayTunnel
     *     The overlay_tunnel
     */
    @JsonProperty("overlay_tunnel")
    public void setOverlayTunnel(String overlayTunnel) {
        this.overlayTunnel = overlayTunnel;
    }

    public VswitchCapabilities withOverlayTunnel(String overlayTunnel) {
        this.overlayTunnel = overlayTunnel;
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

    public VswitchCapabilities withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(version).append(type).append(overlayTunnel).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VswitchCapabilities) == false) {
            return false;
        }
        VswitchCapabilities rhs = ((VswitchCapabilities) other);
        return new EqualsBuilder().append(version, rhs.version).append(type, rhs.type).append(overlayTunnel, rhs.overlayTunnel).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
