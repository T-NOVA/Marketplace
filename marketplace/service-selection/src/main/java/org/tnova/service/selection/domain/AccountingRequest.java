package org.tnova.service.selection.domain;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonPropertyOrder( {
    "instanceId",
    "productId",
    "agreementId",
    "relatives",
    "productType",
    "flavour",
    "providerId",
    "clientId",
    "status",
    "billingModel",
    "period",
    "priceUnit",
    "periodCost",
    "setupCost",
    "renew" } )
public class AccountingRequest
{

    @JsonProperty( "instanceId" )
    private String instanceId;
    @JsonProperty( "productId" )
    private String productId;
    @JsonProperty( "agreementId" )
    private String agreementId;
    @JsonProperty( "relatives" )
    private String relatives;
    @JsonProperty( "productType" )
    private String productType;
    @JsonProperty( "flavour" )
    private String flavour;
    @JsonProperty( "providerId" )
    private String providerId;
    @JsonProperty( "clientId" )
    private String clientId;
    @JsonProperty( "status" )
    private String status;
    @JsonProperty( "billingModel" )
    private String billingModel;
    @JsonProperty( "period" )
    private String period;
    @JsonProperty( "priceUnit" )
    private String priceUnit;
    @JsonProperty( "periodCost" )
    private double periodCost;
    @JsonProperty( "setupCost" )
    private double setupCost;
    @JsonProperty( "renew" )
    private boolean renew;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public AccountingRequest() {}

    public AccountingRequest( String instanceId, String productId, String agreementId, String relatives,
        String productType, String flavour, String providerId, String clientId, String status, String billingModel,
        String period, String priceUnit, double periodCost, double setupCost, boolean renew )
    {
        this.instanceId = instanceId;
        this.productId = productId;
        this.agreementId = agreementId;
        this.relatives = relatives;
        this.productType = productType;
        this.flavour = flavour;
        this.providerId = providerId;
        this.clientId = clientId;
        this.status = status;
        this.billingModel = billingModel;
        this.period = period;
        this.priceUnit = priceUnit;
        this.periodCost = periodCost;
        this.setupCost = setupCost;
        this.renew = renew;
    }

    @JsonProperty( "instanceId" )
    public String getInstanceId()
    {
        return instanceId;
    }

    @JsonProperty( "instanceId" )
    public void setInstanceId( String instanceId )
    {
        this.instanceId = instanceId;
    }

    public AccountingRequest withInstanceId( String instanceId )
    {
        this.instanceId = instanceId;
        return this;
    }

    @JsonProperty( "productId" )
    public String getProductId()
    {
        return productId;
    }

    @JsonProperty( "productId" )
    public void setProductId( String productId )
    {
        this.productId = productId;
    }

    public AccountingRequest withProductId( String productId )
    {
        this.productId = productId;
        return this;
    }


    @JsonProperty( "agreementId" )
    public String getAgreementId()
    {
        return agreementId;
    }

    @JsonProperty( "agreementId" )
    public void setAgreementId( String agreementId )
    {
        this.agreementId = agreementId;
    }

    public AccountingRequest withAgreementId( String agreementId )
    {
        this.agreementId = agreementId;
        return this;
    }

    @JsonProperty( "relatives" )
    public String getRelatives()
    {
        return relatives;
    }

    @JsonProperty( "relatives" )
    public void setRelatives( String relatives )
    {
        this.relatives = relatives;
    }

    public AccountingRequest withRelatives( String relatives )
    {
        this.relatives = relatives;
        return this;
    }

    @JsonProperty( "productType" )
    public String getProductType()
    {
        return productType;
    }

    @JsonProperty( "productType" )
    public void setProductType( String productType )
    {
        this.productType = productType;
    }

    public AccountingRequest withProductType( String productType )
    {
        this.productType = productType;
        return this;
    }

    @JsonProperty( "flavour" )
    public String getFlavour()
    {
        return flavour;
    }

    /**
     * @param flavour The flavour
     */
    @JsonProperty( "flavour" )
    public void setFlavour( String flavour )
    {
        this.flavour = flavour;
    }

    public AccountingRequest withFlavour( String flavour )
    {
        this.flavour = flavour;
        return this;
    }

    /**
     * @return The providerId
     */
    @JsonProperty( "providerId" )
    public String getProviderId()
    {
        return providerId;
    }

    /**
     * @param providerId The providerId
     */
    @JsonProperty( "providerId" )
    public void setProviderId( String providerId )
    {
        this.providerId = providerId;
    }

    public AccountingRequest withProviderId( String providerId )
    {
        this.providerId = providerId;
        return this;
    }

    /**
     * @return The clientId
     */
    @JsonProperty( "clientId" )
    public String getClientId()
    {
        return clientId;
    }

    /**
     * @param clientId The clientId
     */
    @JsonProperty( "clientId" )
    public void setClientId( String clientId )
    {
        this.clientId = clientId;
    }

    public AccountingRequest withClientId( String clientId )
    {
        this.clientId = clientId;
        return this;
    }

    /**
     * @return The status
     */
    @JsonProperty( "status" )
    public String getStatus()
    {
        return status;
    }

    /**
     * @param status The status
     */
    @JsonProperty( "status" )
    public void setStatus( String status )
    {
        this.status = status;
    }

    public AccountingRequest withStatus( String status )
    {
        this.status = status;
        return this;
    }

    /**
     * @return The billingModel
     */
    @JsonProperty( "billingModel" )
    public String getBillingModel()
    {
        return billingModel;
    }

    /**
     * @param billingModel The billingModel
     */
    @JsonProperty( "billingModel" )
    public void setBillingModel( String billingModel )
    {
        this.billingModel = billingModel;
    }

    public AccountingRequest withBillingModel( String billingModel )
    {
        this.billingModel = billingModel;
        return this;
    }

    /**
     * @return The period
     */
    @JsonProperty( "period" )
    public String getPeriod()
    {
        return period;
    }

    /**
     * @param period The period
     */
    @JsonProperty( "period" )
    public void setPeriod( String period )
    {
        this.period = period;
    }

    public AccountingRequest withPeriod( String period )
    {
        this.period = period;
        return this;
    }

    /**
     * @return The priceUnit
     */
    @JsonProperty( "priceUnit" )
    public String getPriceUnit()
    {
        return priceUnit;
    }

    /**
     * @param priceUnit The priceUnit
     */
    @JsonProperty( "priceUnit" )
    public void setPriceUnit( String priceUnit )
    {
        this.priceUnit = priceUnit;
    }

    public AccountingRequest withPriceUnit( String priceUnit )
    {
        this.priceUnit = priceUnit;
        return this;
    }

    /**
     * @return The periodCost
     */
    @JsonProperty( "periodCost" )
    public double getPeriodCost()
    {
        return periodCost;
    }

    /**
     * @param periodCost The periodCost
     */
    @JsonProperty( "periodCost" )
    public void setPeriodCost( double periodCost )
    {
        this.periodCost = periodCost;
    }

    public AccountingRequest withPeriodCost( double periodCost )
    {
        this.periodCost = periodCost;
        return this;
    }

    /**
     * @return The setupCost
     */
    @JsonProperty( "setupCost" )
    public double getSetupCost()
    {
        return setupCost;
    }

    /**
     * @param setupCost The setupCost
     */
    @JsonProperty( "setupCost" )
    public void setSetupCost( double setupCost )
    {
        this.setupCost = setupCost;
    }

    public AccountingRequest withSetupCost( double setupCost )
    {
        this.setupCost = setupCost;
        return this;
    }

    /**
     * @return The renew
     */
    @JsonProperty( "renew" )
    public boolean isRenew()
    {
        return renew;
    }

    /**
     * @param renew The renew
     */
    @JsonProperty( "renew" )
    public void setRenew( boolean renew )
    {
        this.renew = renew;
    }

    public AccountingRequest withRenew( boolean renew )
    {
        this.renew = renew;
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

    public AccountingRequest withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( instanceId ).append( productId ).append( agreementId ).append( relatives )
            .append( productType ).append( flavour ).append( providerId ).append( clientId ).append( status )
            .append( billingModel ).append( period ).append( priceUnit ).append( periodCost ).append( setupCost )
            .append( renew ).append( additionalProperties ).toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof AccountingRequest ) == false )
        {
            return false;
        }
        AccountingRequest rhs = ( (AccountingRequest) other );
        return new EqualsBuilder().append( instanceId, rhs.instanceId ).append( productId, rhs.productId )
            .append( agreementId, rhs.agreementId ).append( relatives, rhs.relatives )
            .append( productType, rhs.productType ).append( flavour, rhs.flavour ).append( providerId, rhs.providerId )
            .append( clientId, rhs.clientId ).append( status, rhs.status ).append( billingModel, rhs.billingModel )
            .append( period, rhs.period ).append( priceUnit, rhs.priceUnit ).append( periodCost, rhs.periodCost )
            .append( setupCost, rhs.setupCost ).append( renew, rhs.renew )
            .append( additionalProperties, rhs.additionalProperties ).isEquals();
    }

}
