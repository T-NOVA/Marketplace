package eu.atos.sla.monitoring.simple;

import java.util.Date;

import eu.atos.sla.monitoring.IMonitoringMetric;

/**
 * Simple implementation of IMonitoringMetric that assumes the value is a double.
 */
public class MonitoringMetric implements IMonitoringMetric {

	private String metricKey;
	private String metricValue;
	private Date date;


	public MonitoringMetric(String metricKey, double metricValue, Date date) {

		this.metricKey = metricKey;
		this.metricValue = String.valueOf(metricValue);
		this.date = date;
	}

	@Override
	public String getMetricKey() {

		return metricKey;
	}

	@Override
	public String getMetricValue() {

		return metricValue;
	}

	@Override
	public Date getDate() {

		return date;
	}

	public void setMetricKey(String metricKey) {
		this.metricKey = metricKey;
	}

	public void setMetricValue(double metricValue) {
		this.metricValue = String.valueOf(metricValue);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
}
