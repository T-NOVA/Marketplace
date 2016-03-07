package org.tnova.service.selection.domain;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonPropertyOrder( {
    "ns_id",
    "customer_id",
    "nap_id",
    "flavor_id",
    "callbackUrl" } )
public class NetworkServiceActivationRequest
{

    @JsonProperty( "ns_id" )
    private String nsId;
    @JsonProperty( "customer_id" )
    private String customerId;
    @JsonProperty( "nap_id" )
    private String napId;
    @JsonProperty( "flavor_id" )
    private String flavorId;

    @JsonProperty( "callbackUrl" )
    private String callbackUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty( "ns_id" )
    public String getNsId()
    {
        return nsId;
    }

    @JsonProperty( "ns_id" )
    public void setNsId( String nsId )
    {
        this.nsId = nsId;
    }

    @JsonProperty( "customer_id" )
    public String getCustomerId()
    {
        return customerId;
    }

    @JsonProperty( "customer_id" )
    public void setCustomerId( String customerId )
    {
        this.customerId = customerId;
    }

    @JsonProperty( "nap_id" )
    public String getNapId()
    {
        return napId;
    }

    @JsonProperty( "nap_id" )
    public void setNapId( String napId )
    {
        this.napId = napId;
    }

     @JsonProperty( "flavor_id")
    public String getFlavorId()
    {
        return flavorId;
    }

    @JsonProperty( "flavor_id" )
    public void setFlavorId( String flavorId )
    {
        this.flavorId = flavorId;
    }


    @JsonProperty( "callbackUrl" )
    public String getCallbackUrl()
    {
        return callbackUrl;
    }

    @JsonProperty( "callbackUrl" )
    public void setCallbackUrl( String callbackUrl )
    {
        this.callbackUrl = callbackUrl;
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

}
