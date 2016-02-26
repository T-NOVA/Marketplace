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
 * A POJO Object that stores all the information from a Violation to be attached to the corresponding penalty
 * 
 * @author Javier Melian - Atos
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "violation")
public class PenaltyViolation  {

	@XmlElement(name = "metric_name")
	private String metricName;
	@XmlElement(name = "expected_value")
	private String expectedValue;
	@XmlElement(name = "actual_value")
	private String actualValue;

	public PenaltyViolation() {
	}

	public PenaltyViolation(String expectedValue, String actualValue, String kpiName) {
		this.expectedValue = expectedValue;
		this.actualValue = actualValue;
		this.metricName = kpiName;
	}

	@Column(name = "metric_name")
	public String getKpiName() {
		return metricName;
	}

	public void setKpiName(String metricName) {
		this.metricName = metricName;
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
