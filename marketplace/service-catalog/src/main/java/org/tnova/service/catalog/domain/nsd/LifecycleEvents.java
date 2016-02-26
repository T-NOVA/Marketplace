
/*
 * Copyright 2016  CloudStreet Oy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tnova.service.catalog.domain.nsd;

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
@JsonPropertyOrder({
    "start",
    "stop",
    "scale_out"
})
public class LifecycleEvents {

    @JsonProperty("start")
    private List<Start> start = new ArrayList<Start>();
    @JsonProperty("stop")
    private List<Object> stop = new ArrayList<Object>();
    @JsonProperty("scale_out")
    private List<Object> scaleOut = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public LifecycleEvents() {
    }

    /**
     * 
     * @param stop
     * @param start
     * @param scaleOut
     */
    public LifecycleEvents(List<Start> start, List<Object> stop, List<Object> scaleOut) {
        this.start = start;
        this.stop = stop;
        this.scaleOut = scaleOut;
    }

    /**
     * 
     * @return
     *     The start
     */
    @JsonProperty("start")
    public List<Start> getStart() {
        return start;
    }

    /**
     * 
     * @param start
     *     The start
     */
    @JsonProperty("start")
    public void setStart(List<Start> start) {
        this.start = start;
    }

    public LifecycleEvents withStart(List<Start> start) {
        this.start = start;
        return this;
    }

    /**
     * 
     * @return
     *     The stop
     */
    @JsonProperty("stop")
    public List<Object> getStop() {
        return stop;
    }

    /**
     * 
     * @param stop
     *     The stop
     */
    @JsonProperty("stop")
    public void setStop(List<Object> stop) {
        this.stop = stop;
    }

    public LifecycleEvents withStop(List<Object> stop) {
        this.stop = stop;
        return this;
    }

    /**
     * 
     * @return
     *     The scaleOut
     */
    @JsonProperty("scale_out")
    public List<Object> getScaleOut() {
        return scaleOut;
    }

    /**
     * 
     * @param scaleOut
     *     The scale_out
     */
    @JsonProperty("scale_out")
    public void setScaleOut(List<Object> scaleOut) {
        this.scaleOut = scaleOut;
    }

    public LifecycleEvents withScaleOut(List<Object> scaleOut) {
        this.scaleOut = scaleOut;
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

    public LifecycleEvents withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(start).append(stop).append(scaleOut).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LifecycleEvents) == false) {
            return false;
        }
        LifecycleEvents rhs = ((LifecycleEvents) other);
        return new EqualsBuilder().append(start, rhs.start).append(stop, rhs.stop).append(scaleOut, rhs.scaleOut).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
