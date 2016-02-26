package eu.atos.sla.monitoring;

import java.util.Date;

/**
 * Represents a metric to be evaluated by the SLA module.
 * 
 * @author rsosa
 *
 */
public interface IMonitoringMetric {

	String getMetricKey();
	String getMetricValue();
	Date getDate();
}
