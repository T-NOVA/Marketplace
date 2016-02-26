package eu.atos.sla.evaluation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.IViolation;
import eu.atos.sla.evaluation.constraint.IConstraintEvaluator;
import eu.atos.sla.evaluation.guarantee.GuaranteeTermEvaluator;
import eu.atos.sla.evaluation.guarantee.IBusinessValuesEvaluator;
import eu.atos.sla.evaluation.guarantee.IServiceLevelEvaluator;
import eu.atos.sla.evaluation.guarantee.GuaranteeTermEvaluator.GuaranteeTermEvaluationResult;
import eu.atos.sla.monitoring.IMonitoringMetric;

/**
 * Evaluates an agreement, obtaining QoS violations and penalties.
 *
 * The process:
 * <ul>
 * <li>Check what metrics do not fulfill the service levels (breaches).
 * <li>Check and raise the violations according to the found breaches and policies (if any) of service levels.
 * <li>Check and raise the compensations (business violations) that are derived from the raised violations.
 * </ul>
 * 
 * The result is a map that contains for each guarantee term, the list of violations and compensations that were
 * detected.
 * 
 * There are two possible inputs:
 * - metrics (monitoring provides raw data)
 * - violations (smart monitoring that provides violations)
 * 
 * Usage:
 * <pre>
 * AgreementEvaluator ae = new AgreementEvaluator();
 * ae.setGuaranteeTermEvaluator(...)
 * 
 * ae.evaluate(...)
 * </pre>
 * @author rsosa
 *
 * @see IConstraintEvaluator
 * @see GuaranteeTermEvaluator
 * @see IServiceLevelEvaluator
 * @see IBusinessValuesEvaluator
 */
public class AgreementEvaluator {

	private GuaranteeTermEvaluator termEval;

	public AgreementEvaluator() {
	}
	
	/**
	 * Evaluate if the metrics fulfill the agreement's service levels.
	 * @param agreement Agreement to evaluate.
	 * @param metricsMap Contains the list of metrics to check for each guarantee term. 
	 * @return list of violations and compensations detected.
	 */
	public Map<IGuaranteeTerm, GuaranteeTermEvaluationResult> evaluate(IAgreement agreement,
			Map<IGuaranteeTerm, List<IMonitoringMetric>> metricsMap) {

		checkInitialized(false);
		
		Map<IGuaranteeTerm,GuaranteeTermEvaluationResult> result = 
				new HashMap<IGuaranteeTerm,GuaranteeTermEvaluationResult>();
		
		Date now = new Date();
		for (IGuaranteeTerm term : metricsMap.keySet()) {

			List<IMonitoringMetric> metrics = metricsMap.get(term);
			if (metrics.size() > 0) {
				GuaranteeTermEvaluationResult aux = termEval.evaluate(agreement, term, metrics, now);
				result.put(term, aux);
			}
		}

		return result;
	}

	/**
	 * Evaluate if the detected violations imply any compensation.
	 * @param agreement Agreement to evaluate.
	 * @param violationsMap Contains the list of metrics to check for each guarantee term. 
	 * @return list of violations and compensations detected.
	 */
	public Map<IGuaranteeTerm, GuaranteeTermEvaluationResult> evaluateBusiness(IAgreement agreement,
			Map<IGuaranteeTerm, List<IViolation>> violationsMap) {
		
		checkInitialized(false);
		
		Map<IGuaranteeTerm,GuaranteeTermEvaluationResult> result = 
				new HashMap<IGuaranteeTerm,GuaranteeTermEvaluationResult>();
		
		Date now = new Date();
		for (IGuaranteeTerm term : violationsMap.keySet()) {

			List<IViolation> violations = violationsMap.get(term);
			if (violations.size() > 0) {
				GuaranteeTermEvaluationResult aux = termEval.evaluateBusiness(agreement, term, violations, now);
				result.put(term, aux);
			}
		}
		return result;
	}
	
	private void checkInitialized(boolean checkRetriever) {
		if (termEval == null) {
			throw new NullPointerException("guaranteeTermEvaluator has not been set");
		}
	}
	
	public GuaranteeTermEvaluator getGuaranteeTermEvaluator() {
		return termEval;
	}

	public void setGuaranteeTermEvaluator(GuaranteeTermEvaluator termEval) {
		this.termEval = termEval;
	}
}