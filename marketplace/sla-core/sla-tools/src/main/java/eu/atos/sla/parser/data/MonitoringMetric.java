package eu.atos.sla.parser.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eu.atos.sla.parser.DateTimeDeserializerJSON;
import eu.atos.sla.parser.DateTimeSerializerJSON;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "metric")
public class MonitoringMetric {

	@XmlElement(name = "key")
	private String metricKey;
	
	@XmlElement(name = "value")
	private String metricValue;

	@JsonSerialize(using=DateTimeSerializerJSON.class)
	@JsonDeserialize(using=DateTimeDeserializerJSON.class)
	@XmlElement(name = "datetime")	
	private Date date;

	
	public String getMetricKey() {
		return metricKey;
	}

	public String getMetricValue() {
		return metricValue;
	}

	public Date getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		return String.format(
				"MonitoringMetric[metricKey='%s', metricValue='%s', date='%s'(%d)]",
				metricKey, metricValue, date, date.getTime());
	}
}
