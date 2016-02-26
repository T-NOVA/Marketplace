
package org.tnova.service.selection.domain.nsd;

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
    "number_of_endpoints",
    "virtual_links"
})
public class Vld {

    @JsonProperty("number_of_endpoints")
    private long numberOfEndpoints;
    @JsonProperty("virtual_links")
    private List<VirtualLink> virtualLinks = new ArrayList<VirtualLink>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Vld() {
    }

    /**
     * 
     * @param numberOfEndpoints
     * @param virtualLinks
     */
    public Vld(long numberOfEndpoints, List<VirtualLink> virtualLinks) {
        this.numberOfEndpoints = numberOfEndpoints;
        this.virtualLinks = virtualLinks;
    }

    /**
     * 
     * @return
     *     The numberOfEndpoints
     */
    @JsonProperty("number_of_endpoints")
    public long getNumberOfEndpoints() {
        return numberOfEndpoints;
    }

    /**
     * 
     * @param numberOfEndpoints
     *     The number_of_endpoints
     */
    @JsonProperty("number_of_endpoints")
    public void setNumberOfEndpoints(long numberOfEndpoints) {
        this.numberOfEndpoints = numberOfEndpoints;
    }

    public Vld withNumberOfEndpoints(long numberOfEndpoints) {
        this.numberOfEndpoints = numberOfEndpoints;
        return this;
    }

    /**
     * 
     * @return
     *     The virtualLinks
     */
    @JsonProperty("virtual_links")
    public List<VirtualLink> getVirtualLinks() {
        return virtualLinks;
    }

    /**
     * 
     * @param virtualLinks
     *     The virtual_links
     */
    @JsonProperty("virtual_links")
    public void setVirtualLinks(List<VirtualLink> virtualLinks) {
        this.virtualLinks = virtualLinks;
    }

    public Vld withVirtualLinks(List<VirtualLink> virtualLinks) {
        this.virtualLinks = virtualLinks;
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

    public Vld withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(numberOfEndpoints).append(virtualLinks).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Vld) == false) {
            return false;
        }
        Vld rhs = ((Vld) other);
        return new EqualsBuilder().append(numberOfEndpoints, rhs.numberOfEndpoints).append(virtualLinks, rhs.virtualLinks).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
