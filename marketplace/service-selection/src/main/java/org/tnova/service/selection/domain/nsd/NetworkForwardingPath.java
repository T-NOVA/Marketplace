
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
@JsonPropertyOrder({
    "nfp_id",
    "graph",
    "connection_points",
    "constituent_vnfs"
})
public class NetworkForwardingPath {

    @JsonProperty("nfp_id")
    private String nfpId;
    @JsonProperty("graph")
    private List<String> graph = new ArrayList<String>();
    @JsonProperty("connection_points")
    private List<String> connectionPoints = new ArrayList<String>();
    @JsonProperty("constituent_vnfs")
    private List<ConstituentVnf> constituentVnfs = new ArrayList<ConstituentVnf>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public NetworkForwardingPath() {
    }

    /**
     * 
     * @param graph
     * @param connectionPoints
     * @param nfpId
     * @param constituentVnfs
     */
    public NetworkForwardingPath(String nfpId, List<String> graph, List<String> connectionPoints, List<ConstituentVnf> constituentVnfs) {
        this.nfpId = nfpId;
        this.graph = graph;
        this.connectionPoints = connectionPoints;
        this.constituentVnfs = constituentVnfs;
    }

    /**
     * 
     * @return
     *     The nfpId
     */
    @JsonProperty("nfp_id")
    public String getNfpId() {
        return nfpId;
    }

    /**
     * 
     * @param nfpId
     *     The nfp_id
     */
    @JsonProperty("nfp_id")
    public void setNfpId(String nfpId) {
        this.nfpId = nfpId;
    }

    public NetworkForwardingPath withNfpId(String nfpId) {
        this.nfpId = nfpId;
        return this;
    }

    /**
     * 
     * @return
     *     The graph
     */
    @JsonProperty("graph")
    public List<String> getGraph() {
        return graph;
    }

    /**
     * 
     * @param graph
     *     The graph
     */
    @JsonProperty("graph")
    public void setGraph(List<String> graph) {
        this.graph = graph;
    }

    public NetworkForwardingPath withGraph(List<String> graph) {
        this.graph = graph;
        return this;
    }

    /**
     * 
     * @return
     *     The connectionPoints
     */
    @JsonProperty("connection_points")
    public List<String> getConnectionPoints() {
        return connectionPoints;
    }

    /**
     * 
     * @param connectionPoints
     *     The connection_points
     */
    @JsonProperty("connection_points")
    public void setConnectionPoints(List<String> connectionPoints) {
        this.connectionPoints = connectionPoints;
    }

    public NetworkForwardingPath withConnectionPoints(List<String> connectionPoints) {
        this.connectionPoints = connectionPoints;
        return this;
    }

    /**
     * 
     * @return
     *     The constituentVnfs
     */
    @JsonProperty("constituent_vnfs")
    public List<ConstituentVnf> getConstituentVnfs() {
        return constituentVnfs;
    }

    /**
     * 
     * @param constituentVnfs
     *     The constituent_vnfs
     */
    @JsonProperty("constituent_vnfs")
    public void setConstituentVnfs(List<ConstituentVnf> constituentVnfs) {
        this.constituentVnfs = constituentVnfs;
    }

    public NetworkForwardingPath withConstituentVnfs(List<ConstituentVnf> constituentVnfs) {
        this.constituentVnfs = constituentVnfs;
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

    public NetworkForwardingPath withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(nfpId).append(graph).append(connectionPoints).append(constituentVnfs).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NetworkForwardingPath) == false) {
            return false;
        }
        NetworkForwardingPath rhs = ((NetworkForwardingPath) other);
        return new EqualsBuilder().append(nfpId, rhs.nfpId).append(graph, rhs.graph).append(connectionPoints, rhs.connectionPoints).append(constituentVnfs, rhs.constituentVnfs).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
