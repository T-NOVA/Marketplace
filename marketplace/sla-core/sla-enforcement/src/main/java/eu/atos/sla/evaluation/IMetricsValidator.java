package eu.atos.sla.evaluation;

import java.util.Collection;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.monitoring.IMonitoringMetric;


/**
 * Interface that any sla provider has to implement in order to evaluate if any given metrics
 * are breaches or not.
 *  
 * @author rsosa
 * @deprecated
 */
public interface IMetricsValidator {

	/**
	 * Given a constraint, returns the variable (service property) that has to be retrieved from the monitoring
	 * module.
	 * 
	 * It is a restriction of the core that only one variable can be retrieved from monitoring per constraint.
	 * 
	 * Example:
	 * getConstraintVariable("uptime GT 99") -> "uptime"
	 */
	String getConstraintVariable(String constraint);
	
	/**
	 * Check that a constraint is being fulfilled.
	 * @param agrement: Agreement being enforced. The value of a variable may be in the ServiceDescriptionTerms.
	 * @param kpiName: Name of the KPI.
	 * @param metrics: List of metrics retrieved from the monitoring module.
	 * @param constraint: constraint to be enforced.
	 * @return Metrics that do not fulfill the constraint.
	 * 
	 * @see eu.atos.sla.datamodel.IGuaranteeTerm
	 */
	Iterable<IMonitoringMetric> getBreaches(
			IAgreement agrement, String kpiName, Collection<IMonitoringMetric> metrics, String constraint);
}
