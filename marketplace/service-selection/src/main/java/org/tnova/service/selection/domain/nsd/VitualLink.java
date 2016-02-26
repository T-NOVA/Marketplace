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
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "vld_id",
    "root_requirements",
    "leaf_requirements",
    "qos",
    "test_access",
    "connections",
    "connectivity_type"
})
public class VitualLink {

    @JsonProperty("vld_id")
    private String vldId;
    @JsonProperty("root_requirements")
    private String rootRequirements;
    @JsonProperty("leaf_requirements")
    private String leafRequirements;
    @JsonProperty("qos")
    private Qos qos;
    @JsonProperty("test_access")
    private String testAccess;
    @JsonProperty("connections")
    private List<String> connections = new ArrayList<String>();
    @JsonProperty("connectivity_type")
    private String connectivityType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public VitualLink() {}

    public VitualLink(String vldId, String rootRequirements, String leafRequirements, Qos qos, String testAccess, List<String> connections, String connectivityType) {
        this.vldId = vldId;
        this.rootRequirements = rootRequirements;
        this.leafRequirements = leafRequirements;
        this.qos = qos;
        this.testAccess = testAccess;
        this.connections = connections;
        this.connectivityType = connectivityType;
    }

    /**
     * 
     * @return
     *     The vldId
     */
    @JsonProperty("vld_id")
    public String getVldId() {
        return vldId;
    }

    /**
     * 
     * @param vldId
     *     The vld_id
     */
    @JsonProperty("vld_id")
    public void setVldId(String vldId) {
        this.vldId = vldId;
    }

    public VitualLink withVldId(String vldId) {
        this.vldId = vldId;
        return this;
    }

    /**
     * 
     * @return
     *     The rootRequirements
     */
    @JsonProperty("root_requirements")
    public String getRootRequirements() {
        return rootRequirements;
    }

    /**
     * 
     * @param rootRequirements
     *     The root_requirements
     */
    @JsonProperty("root_requirements")
    public void setRootRequirements(String rootRequirements) {
        this.rootRequirements = rootRequirements;
    }

    public VitualLink withRootRequirements(String rootRequirements) {
        this.rootRequirements = rootRequirements;
        return this;
    }

    /**
     * 
     * @return
     *     The leafRequirements
     */
    @JsonProperty("leaf_requirements")
    public String getLeafRequirements() {
        return leafRequirements;
    }

    /**
     * 
     * @param leafRequirements
     *     The leaf_requirements
     */
    @JsonProperty("leaf_requirements")
    public void setLeafRequirements(String leafRequirements) {
        this.leafRequirements = leafRequirements;
    }

    public VitualLink withLeafRequirements(String leafRequirements) {
        this.leafRequirements = leafRequirements;
        return this;
    }

    /**
     * 
     * @return
     *     The qos
     */
    @JsonProperty("qos")
    public Qos getQos() {
        return qos;
    }

    /**
     * 
     * @param qos
     *     The qos
     */
    @JsonProperty("qos")
    public void setQos(Qos qos) {
        this.qos = qos;
    }

    public VitualLink withQos(Qos qos) {
        this.qos = qos;
        return this;
    }

    /**
     * 
     * @return
     *     The testAccess
     */
    @JsonProperty("test_access")
    public String getTestAccess() {
        return testAccess;
    }

    /**
     * 
     * @param testAccess
     *     The test_access
     */
    @JsonProperty("test_access")
    public void setTestAccess(String testAccess) {
        this.testAccess = testAccess;
    }

    public VitualLink withTestAccess(String testAccess) {
        this.testAccess = testAccess;
        return this;
    }

    /**
     * 
     * @return
     *     The connections
     */
    @JsonProperty("connections")
    public List<String> getConnections() {
        return connections;
    }

    /**
     * 
     * @param connections
     *     The connections
     */
    @JsonProperty("connections")
    public void setConnections(List<String> connections) {
        this.connections = connections;
    }

    public VitualLink withConnections(List<String> connections) {
        this.connections = connections;
        return this;
    }

    /**
     * 
     * @return
     *     The connectivityType
     */
    @JsonProperty("connectivity_type")
    public String getConnectivityType() {
        return connectivityType;
    }

    /**
     * 
     * @param connectivityType
     *     The connectivity_type
     */
    @JsonProperty("connectivity_type")
    public void setConnectivityType(String connectivityType) {
        this.connectivityType = connectivityType;
    }

    public VitualLink withConnectivityType(String connectivityType) {
        this.connectivityType = connectivityType;
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

    public VitualLink withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(vldId).append(rootRequirements).append(leafRequirements).append(qos).append(testAccess).append(connections).append(connectivityType).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VitualLink) == false) {
            return false;
        }
        VitualLink rhs = ((VitualLink) other);
        return new EqualsBuilder().append(vldId, rhs.vldId).append(rootRequirements, rhs.rootRequirements).append(leafRequirements, rhs.leafRequirements).append(qos, rhs.qos).append(testAccess, rhs.testAccess).append(connections, rhs.connections).append(connectivityType, rhs.connectivityType).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
