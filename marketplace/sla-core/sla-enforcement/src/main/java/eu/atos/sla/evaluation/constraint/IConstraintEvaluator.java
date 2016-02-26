package eu.atos.sla.evaluation.constraint;

import java.util.List;

import eu.atos.sla.monitoring.IMonitoringMetric;

/**
 * Evaluates if a list of metrics fulfill the constraint of a service level.
 * 
 * The constraint evaluator needs to parse the service level.
 * 
 * @author rsosa
 *
 */
public interface IConstraintEvaluator {
	
	/**
	 * Evaluate what metrics are considered breaches.
	 * @param kpiName KPI of the Service Level.
	 * @param constraint to fulfill.
	 * @param metrics to evaluate
	 * @return input metrics that do not fulfill the constraint.
	 * 
	 * @see eu.atos.sla.datamodel.IGuaranteeTerm
	 */
	List<IMonitoringMetric> evaluate(String kpiName, String constraint, List<IMonitoringMetric> metrics);

	/**
	 * Given a constraint, returns the variable (service property) that has to be retrieved from the monitoring
	 * module.
	 * 
	 * It is a restriction of the core that only one variable can be retrieved from monitoring per constraint
	 * (although some hacks could be performed to overcome this limitation).
	 * 
	 * Example:
	 * getConstraintVariable("uptime GT 99") -> "uptime"
	 */
	String getConstraintVariable(String constraint);

	
}
