
package org.tnova.service.selection.domain.vnf;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.validation.Valid;
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
    "start",
    "stop",
    "restart"
})
public class Events {

    @JsonProperty("start")
    @Valid
    private Start start;
    @JsonProperty("stop")
    @Valid
    private Stop stop;
    @JsonProperty("restart")
    @Valid
    private Restart restart;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Events() {
    }

    /**
     * 
     * @param stop
     * @param restart
     * @param start
     */
    public Events(Start start, Stop stop, Restart restart) {
        this.start = start;
        this.stop = stop;
        this.restart = restart;
    }

    /**
     * 
     * @return
     *     The start
     */
    @JsonProperty("start")
    public Start getStart() {
        return start;
    }

    /**
     * 
     * @param start
     *     The start
     */
    @JsonProperty("start")
    public void setStart(Start start) {
        this.start = start;
    }

    public Events withStart(Start start) {
        this.start = start;
        return this;
    }

    /**
     * 
     * @return
     *     The stop
     */
    @JsonProperty("stop")
    public Stop getStop() {
        return stop;
    }

    /**
     * 
     * @param stop
     *     The stop
     */
    @JsonProperty("stop")
    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public Events withStop(Stop stop) {
        this.stop = stop;
        return this;
    }

    /**
     * 
     * @return
     *     The restart
     */
    @JsonProperty("restart")
    public Restart getRestart() {
        return restart;
    }

    /**
     * 
     * @param restart
     *     The restart
     */
    @JsonProperty("restart")
    public void setRestart(Restart restart) {
        this.restart = restart;
    }

    public Events withRestart(Restart restart) {
        this.restart = restart;
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

    public Events withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(start).append(stop).append(restart).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Events) == false) {
            return false;
        }
        Events rhs = ((Events) other);
        return new EqualsBuilder().append(start, rhs.start).append(stop, rhs.stop).append(restart, rhs.restart).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
