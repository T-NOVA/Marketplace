package org.tnova.service.selection.domain.instantiate;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonPropertyOrder( { "pop_id", "urls", "tenant_name", "tenant_id", "username", "password", "user_id", "token",
    "security_group_id" } )
public class Authentication
{

    @JsonProperty( "pop_id" )
    private String popId;
    @JsonProperty( "urls" )
    @Valid
    private Urls urls;
    @JsonProperty( "tenant_name" )
    private String tenantName;
    @JsonProperty( "tenant_id" )
    private String tenantId;
    @JsonProperty( "username" )
    private String username;
    @JsonProperty( "password" )
    private String password;
    @JsonProperty( "user_id" )
    private String userId;
    @JsonProperty( "token" )
    private String token;
    @JsonProperty( "security_group_id" )
    private long securityGroupId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Authentication()
    {
    }

    /**
     * @param tenantId
     * @param username
     * @param token
     * @param urls
     * @param userId
     * @param securityGroupId
     * @param password
     * @param tenantName
     * @param popId
     */
    public Authentication( String popId, Urls urls, String tenantName, String tenantId, String username,
        String password, String userId, String token, long securityGroupId )
    {
        this.popId = popId;
        this.urls = urls;
        this.tenantName = tenantName;
        this.tenantId = tenantId;
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.token = token;
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

    public Authentication withPopId( String popId )
    {
        this.popId = popId;
        return this;
    }

    /**
     * @return The urls
     */
    @JsonProperty( "urls" )
    public Urls getUrls()
    {
        return urls;
    }

    /**
     * @param urls The urls
     */
    @JsonProperty( "urls" )
    public void setUrls( Urls urls )
    {
        this.urls = urls;
    }

    public Authentication withUrls( Urls urls )
    {
        this.urls = urls;
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

    public Authentication withTenantName( String tenantName )
    {
        this.tenantName = tenantName;
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

    public Authentication withTenantId( String tenantId )
    {
        this.tenantId = tenantId;
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

    public Authentication withUsername( String username )
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

    public Authentication withPassword( String password )
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

    public Authentication withUserId( String userId )
    {
        this.userId = userId;
        return this;
    }

    /**
     * @return The token
     */
    @JsonProperty( "token" )
    public String getToken()
    {
        return token;
    }

    /**
     * @param token The token
     */
    @JsonProperty( "token" )
    public void setToken( String token )
    {
        this.token = token;
    }

    public Authentication withToken( String token )
    {
        this.token = token;
        return this;
    }

    /**
     * @return The securityGroupId
     */
    @JsonProperty( "security_group_id" )
    public long getSecurityGroupId()
    {
        return securityGroupId;
    }

    /**
     * @param securityGroupId The security_group_id
     */
    @JsonProperty( "security_group_id" )
    public void setSecurityGroupId( long securityGroupId )
    {
        this.securityGroupId = securityGroupId;
    }

    public Authentication withSecurityGroupId( long securityGroupId )
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

    public Authentication withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( popId ).append( urls ).append( tenantName ).append( tenantId )
            .append( username ).append( password ).append( userId ).append( token ).append( securityGroupId )
            .append( additionalProperties ).toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof Authentication ) == false )
        {
            return false;
        }
        Authentication rhs = ( (Authentication) other );
        return new EqualsBuilder().append( popId, rhs.popId ).append( urls, rhs.urls )
            .append( tenantName, rhs.tenantName ).append( tenantId, rhs.tenantId ).append( username, rhs.username )
            .append( password, rhs.password ).append( userId, rhs.userId ).append( token, rhs.token )
            .append( securityGroupId, rhs.securityGroupId ).append( additionalProperties, rhs.additionalProperties )
            .isEquals();
    }

}
