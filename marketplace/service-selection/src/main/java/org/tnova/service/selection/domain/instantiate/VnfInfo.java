package org.tnova.service.selection.domain.instantiate;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude( JsonInclude.Include.NON_NULL )
@Generated( "org.jsonschema2pojo" )
@JsonPropertyOrder( { "pop_id", "tenant_id", "tenant_name", "username", "password", "user_id", "security_group_id" } )
public class VnfInfo
{

    @JsonProperty( "pop_id" )
    private String popId;
    @JsonProperty( "tenant_id" )
    private String tenantId;
    @JsonProperty( "tenant_name" )
    private String tenantName;
    @JsonProperty( "username" )
    private String username;
    @JsonProperty( "password" )
    private String password;
    @JsonProperty( "user_id" )
    private String userId;
    @JsonProperty( "security_group_id" )
    private String securityGroupId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public VnfInfo()
    {
    }

    /**
     * @param tenantId
     * @param username
     * @param userId
     * @param securityGroupId
     * @param password
     * @param tenantName
     * @param popId
     */
    public VnfInfo( String popId, String tenantId, String tenantName, String username, String password, String userId,
        String securityGroupId )
    {
        this.popId = popId;
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.securityGroupId = securityGroupId;
    }

    /**
     * @return The popId
     */
    @JsonProperty( "pop_id" )
    public String getPopId()
    {
        return popId;
    }

    /**
     * @param popId The pop_id
     */
    @JsonProperty( "pop_id" )
    public void setPopId( String popId )
    {
        this.popId = popId;
    }

    public VnfInfo withPopId( String popId )
    {
        this.popId = popId;
        return this;
    }

    /**
     * @return The tenantId
     */
    @JsonProperty( "tenant_id" )
    public String getTenantId()
    {
        return tenantId;
    }

    /**
     * @param tenantId The tenant_id
     */
    @JsonProperty( "tenant_id" )
    public void setTenantId( String tenantId )
    {
        this.tenantId = tenantId;
    }

    public VnfInfo withTenantId( String tenantId )
    {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * @return The tenantName
     */
    @JsonProperty( "tenant_name" )
    public String getTenantName()
    {
        return tenantName;
    }

    /**
     * @param tenantName The tenant_name
     */
    @JsonProperty( "tenant_name" )
    public void setTenantName( String tenantName )
    {
        this.tenantName = tenantName;
    }

    public VnfInfo withTenantName( String tenantName )
    {
        this.tenantName = tenantName;
        return this;
    }

    /**
     * @return The username
     */
    @JsonProperty( "username" )
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username The username
     */
    @JsonProperty( "username" )
    public void setUsername( String username )
    {
        this.username = username;
    }

    public VnfInfo withUsername( String username )
    {
        this.username = username;
        return this;
    }

    /**
     * @return The password
     */
    @JsonProperty( "password" )
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password The password
     */
    @JsonProperty( "password" )
    public void setPassword( String password )
    {
        this.password = password;
    }

    public VnfInfo withPassword( String password )
    {
        this.password = password;
        return this;
    }

    /**
     * @return The userId
     */
    @JsonProperty( "user_id" )
    public String getUserId()
    {
        return userId;
    }

    /**
     * @param userId The user_id
     */
    @JsonProperty( "user_id" )
    public void setUserId( String userId )
    {
        this.userId = userId;
    }

    public VnfInfo withUserId( String userId )
    {
        this.userId = userId;
        return this;
    }

    /**
     * @return The securityGroupId
     */
    @JsonProperty( "security_group_id" )
    public String getSecurityGroupId()
    {
        return securityGroupId;
    }

    /**
     * @param securityGroupId The security_group_id
     */
    @JsonProperty( "security_group_id" )
    public void setSecurityGroupId( String securityGroupId )
    {
        this.securityGroupId = securityGroupId;
    }

    public VnfInfo withSecurityGroupId( String securityGroupId )
    {
        this.securityGroupId = securityGroupId;
        return this;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString( this );
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties()
    {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
    }

    public VnfInfo withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( popId ).append( tenantId ).append( tenantName ).append( username )
            .append( password ).append( userId ).append( securityGroupId ).append( additionalProperties ).toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof VnfInfo ) == false )
        {
            return false;
        }
        VnfInfo rhs = ( (VnfInfo) other );
        return new EqualsBuilder().append( popId, rhs.popId ).append( tenantId, rhs.tenantId )
            .append( tenantName, rhs.tenantName ).append( username, rhs.username ).append( password, rhs.password )
            .append( userId, rhs.userId ).append( securityGroupId, rhs.securityGroupId )
            .append( additionalProperties, rhs.additionalProperties ).isEquals();
    }

}
