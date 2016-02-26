
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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "nsd"
})
public class NetworkService {

    @JsonProperty("nsd")
    private Nsd nsd;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public NetworkService() {
    }

    /**
     * 
     * @param nsd
     */
    public NetworkService(Nsd nsd) {
        this.nsd = nsd;
    }

    /**
     * 
     * @return
     *     The nsd
     */
    @JsonProperty("nsd")
    public Nsd getNsd() {
        return nsd;
    }

    /**
     * 
     * @param nsd
     *     The nsd
     */
    @JsonProperty("nsd")
    public void setNsd(Nsd nsd) {
        this.nsd = nsd;
    }

    public NetworkService withNsd(Nsd nsd) {
        this.nsd = nsd;
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

    public NetworkService withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(nsd).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NetworkService) == false) {
            return false;
        }
        NetworkService rhs = ((NetworkService) other);
        return new EqualsBuilder().append(nsd, rhs.nsd).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
