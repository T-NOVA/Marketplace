
package org.tnova.service.selection.domain.vnf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "leaf_requirement",
    "connectivity_type",
    "vdu_reference",
    "external_access",
    "connection_points_reference",
    "access",
    "alias",
    "root_requirement",
    "dhcp",
    "id",
    "qos"
})
public class Vlink {

    @JsonProperty("leaf_requirement")
    private String leafRequirement;
    @JsonProperty("connectivity_type")
    private String connectivityType;
    @JsonProperty("vdu_reference")
    @Valid
    private List<String> vduReference = new ArrayList<String>();
    @JsonProperty("external_access")
    private boolean externalAccess;
    @JsonProperty("connection_points_reference")
    @Valid
    private List<String> connectionPointsReference = new ArrayList<String>();
    @JsonProperty("access")
    private boolean access;
    @JsonProperty("alias")
    private String alias;
    @JsonProperty("root_requirement")
    private String rootRequirement;
    @JsonProperty("dhcp")
    private boolean dhcp;
    @JsonProperty("id")
    private String id;
    @JsonProperty("qos")
    private String qos;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Vlink() {
    }

    /**
     * 
     * @param id
     * @param rootRequirement
     * @param qos
     * @param leafRequirement
     * @param alias
     * @param dhcp
     * @param externalAccess
     * @param connectionPointsReference
     * @param access
     * @param connectivityType
     * @param vduReference
     */
    public Vlink(String leafRequirement, String connectivityType, List<String> vduReference, boolean externalAccess, List<String> connectionPointsReference, boolean access, String alias, String rootRequirement, boolean dhcp, String id, String qos) {
        this.leafRequirement = leafRequirement;
        this.connectivityType = connectivityType;
        this.vduReference = vduReference;
        this.externalAccess = externalAccess;
        this.connectionPointsReference = connectionPointsReference;
        this.access = access;
        this.alias = alias;
        this.rootRequirement = rootRequirement;
        this.dhcp = dhcp;
        this.id = id;
        this.qos = qos;
    }

    /**
     * 
     * @return
     *     The leafRequirement
     */
    @JsonProperty("leaf_requirement")
    public String getLeafRequirement() {
        return leafRequirement;
    }

    /**
     * 
     * @param leafRequirement
     *     The leaf_requirement
     */
    @JsonProperty("leaf_requirement")
    public void setLeafRequirement(String leafRequirement) {
        this.leafRequirement = leafRequirement;
    }

    public Vlink withLeafRequirement(String leafRequirement) {
        this.leafRequirement = leafRequirement;
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

    public Vlink withConnectivityType(String connectivityType) {
        this.connectivityType = connectivityType;
        return this;
    }

    /**
     * 
     * @return
     *     The vduReference
     */
    @JsonProperty("vdu_reference")
    public List<String> getVduReference() {
        return vduReference;
    }

    /**
     * 
     * @param vduReference
     *     The vdu_reference
     */
    @JsonProperty("vdu_reference")
    public void setVduReference(List<String> vduReference) {
        this.vduReference = vduReference;
    }

    public Vlink withVduReference(List<String> vduReference) {
        this.vduReference = vduReference;
        return this;
    }

    /**
     * 
     * @return
     *     The externalAccess
     */
    @JsonProperty("external_access")
    public boolean isExternalAccess() {
        return externalAccess;
    }

    /**
     * 
     * @param externalAccess
     *     The external_access
     */
    @JsonProperty("external_access")
    public void setExternalAccess(boolean externalAccess) {
        this.externalAccess = externalAccess;
    }

    public Vlink withExternalAccess(boolean externalAccess) {
        this.externalAccess = externalAccess;
        return this;
    }

    /**
     * 
     * @return
     *     The connectionPointsReference
     */
    @JsonProperty("connection_points_reference")
    public List<String> getConnectionPointsReference() {
        return connectionPointsReference;
    }

    /**
     * 
     * @param connectionPointsReference
     *     The connection_points_reference
     */
    @JsonProperty("connection_points_reference")
    public void setConnectionPointsReference(List<String> connectionPointsReference) {
        this.connectionPointsReference = connectionPointsReference;
    }

    public Vlink withConnectionPointsReference(List<String> connectionPointsReference) {
        this.connectionPointsReference = connectionPointsReference;
        return this;
    }

    /**
     * 
     * @return
     *     The access
     */
    @JsonProperty("access")
    public boolean isAccess() {
        return access;
    }

    /**
     * 
     * @param access
     *     The access
     */
    @JsonProperty("access")
    public void setAccess(boolean access) {
        this.access = access;
    }

    public Vlink withAccess(boolean access) {
        this.access = access;
        return this;
    }

    /**
     * 
     * @return
     *     The alias
     */
    @JsonProperty("alias")
    public String getAlias() {
        return alias;
    }

    /**
     * 
     * @param alias
     *     The alias
     */
    @JsonProperty("alias")
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Vlink withAlias(String alias) {
        this.alias = alias;
        return this;
    }

    /**
     * 
     * @return
     *     The rootRequirement
     */
    @JsonProperty("root_requirement")
    public String getRootRequirement() {
        return rootRequirement;
    }

    /**
     * 
     * @param rootRequirement
     *     The root_requirement
     */
    @JsonProperty("root_requirement")
    public void setRootRequirement(String rootRequirement) {
        this.rootRequirement = rootRequirement;
    }

    public Vlink withRootRequirement(String rootRequirement) {
        this.rootRequirement = rootRequirement;
        return this;
    }

    /**
     * 
     * @return
     *     The dhcp
     */
    @JsonProperty("dhcp")
    public boolean isDhcp() {
        return dhcp;
    }

    /**
     * 
     * @param dhcp
     *     The dhcp
     */
    @JsonProperty("dhcp")
    public void setDhcp(boolean dhcp) {
        this.dhcp = dhcp;
    }

    public Vlink withDhcp(boolean dhcp) {
        this.dhcp = dhcp;
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

    public Vlink withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The qos
     */
    @JsonProperty("qos")
    public String getQos() {
        return qos;
    }

    /**
     * 
     * @param qos
     *     The qos
     */
    @JsonProperty("qos")
    public void setQos(String qos) {
        this.qos = qos;
    }

    public Vlink withQos(String qos) {
        this.qos = qos;
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

    public Vlink withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(leafRequirement).append(connectivityType).append(vduReference).append(externalAccess).append(connectionPointsReference).append(access).append(alias).append(rootRequirement).append(dhcp).append(id).append(qos).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Vlink) == false) {
            return false;
        }
        Vlink rhs = ((Vlink) other);
        return new EqualsBuilder().append(leafRequirement, rhs.leafRequirement).append(connectivityType, rhs.connectivityType).append(vduReference, rhs.vduReference).append(externalAccess, rhs.externalAccess).append(connectionPointsReference, rhs.connectionPointsReference).append(access, rhs.access).append(alias, rhs.alias).append(rootRequirement, rhs.rootRequirement).append(dhcp, rhs.dhcp).append(id, rhs.id).append(qos, rhs.qos).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
