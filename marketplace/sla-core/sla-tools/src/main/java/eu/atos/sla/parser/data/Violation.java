package eu.atos.sla.parser.data;

import java.util.Date;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eu.atos.sla.parser.DateTimeDeserializerJSON;
import eu.atos.sla.parser.DateTimeSerializerJSON;

/**
 * A POJO Object that stores all the information from a Violation
 * 
 * @author Pedro Rey - Atos
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "violation")
public class Violation  {

	@XmlElement(name = "id")
	private Long id;
	@XmlElement(name = "uuid")
	private String uuid;
	@XmlElement(name = "contract_uuid")
	private String contractUUID;
	@XmlElement(name = "service_name")
	private String serviceName;
	@XmlElement(name = "service_scope")
	private String serviceScope;
	@XmlElement(name = "metric_name")
	private String metricName;
	@JsonSerialize(using=DateTimeSerializerJSON.class)
	@JsonDeserialize(using=DateTimeDeserializerJSON.class)
	@XmlElement(name = "datetime")
	private Date datetime;
	@XmlElement(name = "expected_value")
	private String expectedValue;
	@XmlElement(name = "actual_value")
	private String actualValue;

	public Violation() {
	}

	public Violation(String expectedValue, String actualValue, String kpiName) {
		this.expectedValue = expectedValue;
		this.actualValue = actualValue;
		this.metricName = kpiName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getContractUuid() {
		return contractUUID;
	}

	public void setContractUuid(String contractUUID) {
		this.contractUUID = contractUUID;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceScope() {
		return serviceScope;
	}

	public void setServiceScope(String serviceScope) {
		this.serviceScope = serviceScope;
	}

	@Column(name = "metric_name")
	public String getKpiName() {
		return metricName;
	}

	public void setKpiName(String metricName) {
		this.metricName = metricName;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getExpectedValue() {
		return expectedValue;
	}

	public void setExpectedValue(String expectedValue) {
		this.expectedValue = expectedValue;
	}

	public String getActualValue() {
		return actualValue;
	}

	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}

}
