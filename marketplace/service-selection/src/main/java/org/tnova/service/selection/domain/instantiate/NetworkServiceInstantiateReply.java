package org.tnova.service.selection.domain.instantiate;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude( JsonInclude.Include.NON_NULL )
@Generated( "org.jsonschema2pojo" )
@JsonPropertyOrder( { "audit_log", "authentication", "auto_scale_policy", "connection_points", "created_at",
    "descriptor_reference", "instantiation_end_time", "instantiation_start_time", "lifecycle_event",
    "lifecycle_event_history", "lifecycle_events", "mapping_time", "marketplace_callback", "monitoring_parameters",
    "notification", "nsd_id", "pnfr", "resource_reservation", "runtime_policy_info", "service_deployment_flavour",
    "status", "updated_at", "vendor", "version", "vlr", "vnf_depedency", "vnf_dependency", "vnffgd", "vnffgr", "vnfrs",
    "id" } )
public class NetworkServiceInstantiateReply
{

    @JsonProperty("audit_log")
    private List<String> auditLog = new ArrayList<String>();

    @JsonProperty( "authentication" )

    private List<Authentication> authentication = new ArrayList<Authentication>();
    @JsonProperty( "auto_scale_policy" )
    @Valid
    private AutoScalePolicy autoScalePolicy;
    @JsonProperty( "connection_points" )
    @Valid
    private List<Object> connectionPoints = new ArrayList<Object>();
    @JsonProperty( "created_at" )
    private String createdAt;
    @JsonProperty( "descriptor_reference" )
    private String descriptorReference;
    @JsonProperty( "instantiation_end_time" )
    private String instantiationEndTime;
    @JsonProperty( "instantiation_start_time" )
    private String instantiationStartTime;
    @JsonProperty( "lifecycle_event" )
    private Object lifecycleEvent;
    @JsonProperty( "lifecycle_event_history" )
    @Valid
    private List<String> lifecycleEventHistory = new ArrayList<String>();
    @JsonProperty( "lifecycle_events" )
    @Valid
    private LifecycleEvents lifecycleEvents;
    @JsonProperty( "mapping_time" )
    private String mappingTime;
    @JsonProperty( "marketplace_callback" )
    private Object marketplaceCallback;
    @JsonProperty( "monitoring_parameters" )
    @Valid
    private List<MonitoringParameter> monitoringParameters = new ArrayList<MonitoringParameter>();
    @JsonProperty( "notification" )
    private String notification;
    @JsonProperty( "nsd_id" )
    private String nsdId;
    @JsonProperty( "pnfr" )
    private Object pnfr;
    @JsonProperty( "resource_reservation" )
    @Valid
    private List<ResourceReservation> resourceReservation = new ArrayList<ResourceReservation>();
    @JsonProperty( "runtime_policy_info" )
    @Valid
    private List<Object> runtimePolicyInfo = new ArrayList<Object>();
    @JsonProperty( "service_deployment_flavour" )
    private String serviceDeploymentFlavour;
    @JsonProperty( "status" )
    private String status;
    @JsonProperty( "updated_at" )
    private String updatedAt;
    @JsonProperty( "vendor" )
    private String vendor;
    @JsonProperty( "version" )
    private String version;
    @JsonProperty( "vlr" )
    @Valid
    private List<Vlr> vlr = new ArrayList<Vlr>();
    @JsonProperty( "vnf_depedency" )
    @Valid
    private List<Object> vnfDepedency = new ArrayList<Object>();
    @JsonProperty( "vnf_dependency" )
    private Object vnfDependency;
    @JsonProperty( "vnffgd" )
    @Valid
    private Vnffgd vnffgd;
    @JsonProperty( "vnffgr" )
    private Object vnffgr;
    @JsonProperty( "vnfrs" )
    private List<Vnfr> vnfrs = new ArrayList<Vnfr>();
    @JsonProperty( "id" )
    private String id;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public NetworkServiceInstantiateReply()
    {
    }

    public NetworkServiceInstantiateReply( List<String> auditLog, List<Authentication> authentication,
        AutoScalePolicy autoScalePolicy, List<Object> connectionPoints, String createdAt, String descriptorReference,
        String instantiationEndTime, String instantiationStartTime, Object lifecycleEvent,
        List<String> lifecycleEventHistory, LifecycleEvents lifecycleEvents, String mappingTime,
        Object marketplaceCallback, List<MonitoringParameter> monitoringParameters, String notification, String nsdId,
        Object pnfr, List<ResourceReservation> resourceReservation, List<Object> runtimePolicyInfo,
        String serviceDeploymentFlavour, String status, String updatedAt, String vendor, String version, List<Vlr> vlr,
        List<Object> vnfDepedency, Object vnfDependency, Vnffgd vnffgd, Object vnffgr, List<Vnfr> vnfrs, String id )
    {
        this.auditLog = auditLog;
        this.authentication = authentication;
        this.autoScalePolicy = autoScalePolicy;
        this.connectionPoints = connectionPoints;
        this.createdAt = createdAt;
        this.descriptorReference = descriptorReference;
        this.instantiationEndTime = instantiationEndTime;
        this.instantiationStartTime = instantiationStartTime;
        this.lifecycleEvent = lifecycleEvent;
        this.lifecycleEventHistory = lifecycleEventHistory;
        this.lifecycleEvents = lifecycleEvents;
        this.mappingTime = mappingTime;
        this.marketplaceCallback = marketplaceCallback;
        this.monitoringParameters = monitoringParameters;
        this.notification = notification;
        this.nsdId = nsdId;
        this.pnfr = pnfr;
        this.resourceReservation = resourceReservation;
        this.runtimePolicyInfo = runtimePolicyInfo;
        this.serviceDeploymentFlavour = serviceDeploymentFlavour;
        this.status = status;
        this.updatedAt = updatedAt;
        this.vendor = vendor;
        this.version = version;
        this.vlr = vlr;
        this.vnfDepedency = vnfDepedency;
        this.vnfDependency = vnfDependency;
        this.vnffgd = vnffgd;
        this.vnffgr = vnffgr;
        this.vnfrs = vnfrs;
        this.id = id;
    }

    /**
     * @return The auditLog
     */
    @JsonProperty( "audit_log" )
    public List<String> getAuditLog()
    {
        return auditLog;
    }

    /**
     * @param auditLog The audit_log
     */
    @JsonProperty( "audit_log" )
    public void setAuditLog( List<String> auditLog )
    {
        this.auditLog = auditLog;
    }

    public NetworkServiceInstantiateReply withAuditLog( List<String> auditLog )
    {
        this.auditLog = auditLog;
        return this;
    }

    /**
     * @return The authentication
     */
    @JsonProperty( "authentication" )
    public List<Authentication> getAuthentication()
    {
        return authentication;
    }

    /**
     * @param authentication The authentication
     */
    @JsonProperty( "authentication" )
    public void setAuthentication( List<Authentication> authentication )
    {
        this.authentication = authentication;
    }

    public NetworkServiceInstantiateReply withAuthentication( List<Authentication> authentication )
    {
        this.authentication = authentication;
        return this;
    }

    /**
     * @return The autoScalePolicy
     */
    @JsonProperty( "auto_scale_policy" )
    public AutoScalePolicy getAutoScalePolicy()
    {
        return autoScalePolicy;
    }

    /**
     * @param autoScalePolicy The auto_scale_policy
     */
    @JsonProperty( "auto_scale_policy" )
    public void setAutoScalePolicy( AutoScalePolicy autoScalePolicy )
    {
        this.autoScalePolicy = autoScalePolicy;
    }

    public NetworkServiceInstantiateReply withAutoScalePolicy( AutoScalePolicy autoScalePolicy )
    {
        this.autoScalePolicy = autoScalePolicy;
        return this;
    }

    /**
     * @return The connectionPoints
     */
    @JsonProperty( "connection_points" )
    public List<Object> getConnectionPoints()
    {
        return connectionPoints;
    }

    /**
     * @param connectionPoints The connection_points
     */
    @JsonProperty( "connection_points" )
    public void setConnectionPoints( List<Object> connectionPoints )
    {
        this.connectionPoints = connectionPoints;
    }

    public NetworkServiceInstantiateReply withConnectionPoints( List<Object> connectionPoints )
    {
        this.connectionPoints = connectionPoints;
        return this;
    }

    /**
     * @return The createdAt
     */
    @JsonProperty( "created_at" )
    public String getCreatedAt()
    {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    @JsonProperty( "created_at" )
    public void setCreatedAt( String createdAt )
    {
        this.createdAt = createdAt;
    }

    public NetworkServiceInstantiateReply withCreatedAt( String createdAt )
    {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * @return The descriptorReference
     */
    @JsonProperty( "descriptor_reference" )
    public String getDescriptorReference()
    {
        return descriptorReference;
    }

    /**
     * @param descriptorReference The descriptor_reference
     */
    @JsonProperty( "descriptor_reference" )
    public void setDescriptorReference( String descriptorReference )
    {
        this.descriptorReference = descriptorReference;
    }

    public NetworkServiceInstantiateReply withDescriptorReference( String descriptorReference )
    {
        this.descriptorReference = descriptorReference;
        return this;
    }

    /**
     * @return The instantiationEndTime
     */
    @JsonProperty( "instantiation_end_time" )
    public String getInstantiationEndTime()
    {
        return instantiationEndTime;
    }

    /**
     * @param instantiationEndTime The instantiation_end_time
     */
    @JsonProperty( "instantiation_end_time" )
    public void setInstantiationEndTime( String instantiationEndTime )
    {
        this.instantiationEndTime = instantiationEndTime;
    }

    public NetworkServiceInstantiateReply withInstantiationEndTime( String instantiationEndTime )
    {
        this.instantiationEndTime = instantiationEndTime;
        return this;
    }

    /**
     * @return The instantiationStartTime
     */
    @JsonProperty( "instantiation_start_time" )
    public String getInstantiationStartTime()
    {
        return instantiationStartTime;
    }

    /**
     * @param instantiationStartTime The instantiation_start_time
     */
    @JsonProperty( "instantiation_start_time" )
    public void setInstantiationStartTime( String instantiationStartTime )
    {
        this.instantiationStartTime = instantiationStartTime;
    }

    public NetworkServiceInstantiateReply withInstantiationStartTime( String instantiationStartTime )
    {
        this.instantiationStartTime = instantiationStartTime;
        return this;
    }

    /**
     * @return The lifecycleEvent
     */
    @JsonProperty( "lifecycle_event" )
    public Object getLifecycleEvent()
    {
        return lifecycleEvent;
    }

    /**
     * @param lifecycleEvent The lifecycle_event
     */
    @JsonProperty( "lifecycle_event" )
    public void setLifecycleEvent( Object lifecycleEvent )
    {
        this.lifecycleEvent = lifecycleEvent;
    }

    public NetworkServiceInstantiateReply withLifecycleEvent( Object lifecycleEvent )
    {
        this.lifecycleEvent = lifecycleEvent;
        return this;
    }

    /**
     * @return The lifecycleEventHistory
     */
    @JsonProperty( "lifecycle_event_history" )
    public List<String> getLifecycleEventHistory()
    {
        return lifecycleEventHistory;
    }

    /**
     * @param lifecycleEventHistory The lifecycle_event_history
     */
    @JsonProperty( "lifecycle_event_history" )
    public void setLifecycleEventHistory( List<String> lifecycleEventHistory )
    {
        this.lifecycleEventHistory = lifecycleEventHistory;
    }

    public NetworkServiceInstantiateReply withLifecycleEventHistory( List<String> lifecycleEventHistory )
    {
        this.lifecycleEventHistory = lifecycleEventHistory;
        return this;
    }

    /**
     * @return The lifecycleEvents
     */
    @JsonProperty( "lifecycle_events" )
    public LifecycleEvents getLifecycleEvents()
    {
        return lifecycleEvents;
    }

    /**
     * @param lifecycleEvents The lifecycle_events
     */
    @JsonProperty( "lifecycle_events" )
    public void setLifecycleEvents( LifecycleEvents lifecycleEvents )
    {
        this.lifecycleEvents = lifecycleEvents;
    }

    public NetworkServiceInstantiateReply withLifecycleEvents( LifecycleEvents lifecycleEvents )
    {
        this.lifecycleEvents = lifecycleEvents;
        return this;
    }

    /**
     * @return The mappingTime
     */
    @JsonProperty( "mapping_time" )
    public String getMappingTime()
    {
        return mappingTime;
    }

    /**
     * @param mappingTime The mapping_time
     */
    @JsonProperty( "mapping_time" )
    public void setMappingTime( String mappingTime )
    {
        this.mappingTime = mappingTime;
    }

    public NetworkServiceInstantiateReply withMappingTime( String mappingTime )
    {
        this.mappingTime = mappingTime;
        return this;
    }

    /**
     * @return The marketplaceCallback
     */
    @JsonProperty( "marketplace_callback" )
    public Object getMarketplaceCallback()
    {
        return marketplaceCallback;
    }

    /**
     * @param marketplaceCallback The marketplace_callback
     */
    @JsonProperty( "marketplace_callback" )
    public void setMarketplaceCallback( Object marketplaceCallback )
    {
        this.marketplaceCallback = marketplaceCallback;
    }

    public NetworkServiceInstantiateReply withMarketplaceCallback( Object marketplaceCallback )
    {
        this.marketplaceCallback = marketplaceCallback;
        return this;
    }

    /**
     * @return The monitoringParameters
     */
    @JsonProperty( "monitoring_parameters" )
    public List<MonitoringParameter> getMonitoringParameters()
    {
        return monitoringParameters;
    }

    /**
     * @param monitoringParameters The monitoring_parameters
     */
    @JsonProperty( "monitoring_parameters" )
    public void setMonitoringParameters( List<MonitoringParameter> monitoringParameters )
    {
        this.monitoringParameters = monitoringParameters;
    }

    public NetworkServiceInstantiateReply withMonitoringParameters( List<MonitoringParameter> monitoringParameters )
    {
        this.monitoringParameters = monitoringParameters;
        return this;
    }

    /**
     * @return The notification
     */
    @JsonProperty( "notification" )
    public String getNotification()
    {
        return notification;
    }

    /**
     * @param notification The notification
     */
    @JsonProperty( "notification" )
    public void setNotification( String notification )
    {
        this.notification = notification;
    }

    public NetworkServiceInstantiateReply withNotification( String notification )
    {
        this.notification = notification;
        return this;
    }

    /**
     * @return The nsdId
     */
    @JsonProperty( "nsd_id" )
    public String getNsdId()
    {
        return nsdId;
    }

    /**
     * @param nsdId The nsd_id
     */
    @JsonProperty( "nsd_id" )
    public void setNsdId( String nsdId )
    {
        this.nsdId = nsdId;
    }

    public NetworkServiceInstantiateReply withNsdId( String nsdId )
    {
        this.nsdId = nsdId;
        return this;
    }

    /**
     * @return The pnfr
     */
    @JsonProperty( "pnfr" )
    public Object getPnfr()
    {
        return pnfr;
    }

    /**
     * @param pnfr The pnfr
     */
    @JsonProperty( "pnfr" )
    public void setPnfr( Object pnfr )
    {
        this.pnfr = pnfr;
    }

    public NetworkServiceInstantiateReply withPnfr( Object pnfr )
    {
        this.pnfr = pnfr;
        return this;
    }

    /**
     * @return The resourceReservation
     */
    @JsonProperty( "resource_reservation" )
    public List<ResourceReservation> getResourceReservation()
    {
        return resourceReservation;
    }

    /**
     * @param resourceReservation The resource_reservation
     */
    @JsonProperty( "resource_reservation" )
    public void setResourceReservation( List<ResourceReservation> resourceReservation )
    {
        this.resourceReservation = resourceReservation;
    }

    public NetworkServiceInstantiateReply withResourceReservation( List<ResourceReservation> resourceReservation )
    {
        this.resourceReservation = resourceReservation;
        return this;
    }

    /**
     * @return The runtimePolicyInfo
     */
    @JsonProperty( "runtime_policy_info" )
    public List<Object> getRuntimePolicyInfo()
    {
        return runtimePolicyInfo;
    }

    /**
     * @param runtimePolicyInfo The runtime_policy_info
     */
    @JsonProperty( "runtime_policy_info" )
    public void setRuntimePolicyInfo( List<Object> runtimePolicyInfo )
    {
        this.runtimePolicyInfo = runtimePolicyInfo;
    }

    public NetworkServiceInstantiateReply withRuntimePolicyInfo( List<Object> runtimePolicyInfo )
    {
        this.runtimePolicyInfo = runtimePolicyInfo;
        return this;
    }

    /**
     * @return The serviceDeploymentFlavour
     */
    @JsonProperty( "service_deployment_flavour" )
    public String getServiceDeploymentFlavour()
    {
        return serviceDeploymentFlavour;
    }

    /**
     * @param serviceDeploymentFlavour The service_deployment_flavour
     */
    @JsonProperty( "service_deployment_flavour" )
    public void setServiceDeploymentFlavour( String serviceDeploymentFlavour )
    {
        this.serviceDeploymentFlavour = serviceDeploymentFlavour;
    }

    public NetworkServiceInstantiateReply withServiceDeploymentFlavour( String serviceDeploymentFlavour )
    {
        this.serviceDeploymentFlavour = serviceDeploymentFlavour;
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

    public NetworkServiceInstantiateReply withStatus( String status )
    {
        this.status = status;
        return this;
    }

    /**
     * @return The updatedAt
     */
    @JsonProperty( "updated_at" )
    public String getUpdatedAt()
    {
        return updatedAt;
    }

    /**
     * @param updatedAt The updated_at
     */
    @JsonProperty( "updated_at" )
    public void setUpdatedAt( String updatedAt )
    {
        this.updatedAt = updatedAt;
    }

    public NetworkServiceInstantiateReply withUpdatedAt( String updatedAt )
    {
        this.updatedAt = updatedAt;
        return this;
    }

    /**
     * @return The vendor
     */
    @JsonProperty( "vendor" )
    public String getVendor()
    {
        return vendor;
    }

    /**
     * @param vendor The vendor
     */
    @JsonProperty( "vendor" )
    public void setVendor( String vendor )
    {
        this.vendor = vendor;
    }

    public NetworkServiceInstantiateReply withVendor( String vendor )
    {
        this.vendor = vendor;
        return this;
    }

    /**
     * @return The version
     */
    @JsonProperty( "version" )
    public String getVersion()
    {
        return version;
    }

    /**
     * @param version The version
     */
    @JsonProperty( "version" )
    public void setVersion( String version )
    {
        this.version = version;
    }

    public NetworkServiceInstantiateReply withVersion( String version )
    {
        this.version = version;
        return this;
    }

    /**
     * @return The vlr
     */
    @JsonProperty( "vlr" )
    public List<Vlr> getVlr()
    {
        return vlr;
    }

    /**
     * @param vlr The vlr
     */
    @JsonProperty( "vlr" )
    public void setVlr( List<Vlr> vlr )
    {
        this.vlr = vlr;
    }

    public NetworkServiceInstantiateReply withVlr( List<Vlr> vlr )
    {
        this.vlr = vlr;
        return this;
    }

    /**
     * @return The vnfDepedency
     */
    @JsonProperty( "vnf_depedency" )
    public List<Object> getVnfDepedency()
    {
        return vnfDepedency;
    }

    /**
     * @param vnfDepedency The vnf_depedency
     */
    @JsonProperty( "vnf_depedency" )
    public void setVnfDepedency( List<Object> vnfDepedency )
    {
        this.vnfDepedency = vnfDepedency;
    }

    public NetworkServiceInstantiateReply withVnfDepedency( List<Object> vnfDepedency )
    {
        this.vnfDepedency = vnfDepedency;
        return this;
    }

    /**
     * @return The vnfDependency
     */
    @JsonProperty( "vnf_dependency" )
    public Object getVnfDependency()
    {
        return vnfDependency;
    }

    /**
     * @param vnfDependency The vnf_dependency
     */
    @JsonProperty( "vnf_dependency" )
    public void setVnfDependency( Object vnfDependency )
    {
        this.vnfDependency = vnfDependency;
    }

    public NetworkServiceInstantiateReply withVnfDependency( Object vnfDependency )
    {
        this.vnfDependency = vnfDependency;
        return this;
    }

    /**
     * @return The vnffgd
     */
    @JsonProperty( "vnffgd" )
    public Vnffgd getVnffgd()
    {
        return vnffgd;
    }

    /**
     * @param vnffgd The vnffgd
     */
    @JsonProperty( "vnffgd" )
    public void setVnffgd( Vnffgd vnffgd )
    {
        this.vnffgd = vnffgd;
    }

    public NetworkServiceInstantiateReply withVnffgd( Vnffgd vnffgd )
    {
        this.vnffgd = vnffgd;
        return this;
    }

    /**
     * @return The vnffgr
     */
    @JsonProperty( "vnffgr" )
    public Object getVnffgr()
    {
        return vnffgr;
    }

    /**
     * @param vnffgr The vnffgr
     */
    @JsonProperty( "vnffgr" )
    public void setVnffgr( Object vnffgr )
    {
        this.vnffgr = vnffgr;
    }

    public NetworkServiceInstantiateReply withVnffgr( Object vnffgr )
    {
        this.vnffgr = vnffgr;
        return this;
    }

    /**
     * @return The vnfrs
     */
    @JsonProperty( "vnfrs" )
    public List<Vnfr> getVnfrs()
    {
        return vnfrs;
    }

    /**
     * @param vnfrs The vnfrs
     */
    @JsonProperty( "vnfrs" )
    public void setVnfrs( List<Vnfr> vnfrs )
    {
        this.vnfrs = vnfrs;
    }

    public NetworkServiceInstantiateReply withVnfrs( List<Vnfr> vnfrs )
    {
        this.vnfrs = vnfrs;
        return this;
    }

    /**
     * @return The id
     */
    @JsonProperty( "id" )
    public String getId()
    {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty( "id" )
    public void setId( String id )
    {
        this.id = id;
    }

    public NetworkServiceInstantiateReply withId( String id )
    {
        this.id = id;
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

    public NetworkServiceInstantiateReply withAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
        return this;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( auditLog ).append( authentication ).append( autoScalePolicy )
            .append( connectionPoints ).append( createdAt ).append( descriptorReference ).append( instantiationEndTime )
            .append( instantiationStartTime ).append( lifecycleEvent ).append( lifecycleEventHistory )
            .append( lifecycleEvents ).append( mappingTime ).append( marketplaceCallback )
            .append( monitoringParameters ).append( notification ).append( nsdId ).append( pnfr )
            .append( resourceReservation ).append( runtimePolicyInfo ).append( serviceDeploymentFlavour )
            .append( status ).append( updatedAt ).append( vendor ).append( version ).append( vlr )
            .append( vnfDepedency ).append( vnfDependency ).append( vnffgd ).append( vnffgr ).append( vnfrs )
            .append( id ).append( additionalProperties ).toHashCode();
    }

    @Override
    public boolean equals( Object other )
    {
        if( other == this )
        {
            return true;
        }
        if( ( other instanceof NetworkServiceInstantiateReply ) == false )
        {
            return false;
        }
        NetworkServiceInstantiateReply rhs = ( (NetworkServiceInstantiateReply) other );
        return new EqualsBuilder().append( auditLog, rhs.auditLog ).append( authentication, rhs.authentication )
            .append( autoScalePolicy, rhs.autoScalePolicy ).append( connectionPoints, rhs.connectionPoints )
            .append( createdAt, rhs.createdAt ).append( descriptorReference, rhs.descriptorReference )
            .append( instantiationEndTime, rhs.instantiationEndTime )
            .append( instantiationStartTime, rhs.instantiationStartTime ).append( lifecycleEvent, rhs.lifecycleEvent )
            .append( lifecycleEventHistory, rhs.lifecycleEventHistory ).append( lifecycleEvents, rhs.lifecycleEvents )
            .append( mappingTime, rhs.mappingTime ).append( marketplaceCallback, rhs.marketplaceCallback )
            .append( monitoringParameters, rhs.monitoringParameters ).append( notification, rhs.notification )
            .append( nsdId, rhs.nsdId ).append( pnfr, rhs.pnfr ).append( resourceReservation, rhs.resourceReservation )
            .append( runtimePolicyInfo, rhs.runtimePolicyInfo )
            .append( serviceDeploymentFlavour, rhs.serviceDeploymentFlavour ).append( status, rhs.status )
            .append( updatedAt, rhs.updatedAt ).append( vendor, rhs.vendor ).append( version, rhs.version )
            .append( vlr, rhs.vlr ).append( vnfDepedency, rhs.vnfDepedency ).append( vnfDependency, rhs.vnfDependency )
            .append( vnffgd, rhs.vnffgd ).append( vnffgr, rhs.vnffgr ).append( vnfrs, rhs.vnfrs ).append( id, rhs.id )
            .append( additionalProperties, rhs.additionalProperties ).isEquals();
    }

}
