package eu.atos.sla.evaluation.guarantee;

import java.util.Date;
import java.util.List;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.ICompensation;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.IViolation;

/**
 * Assesses the compensations that are derived from a list of violations.
 * 
 * @author rsosa
 *
 */
public interface IBusinessValuesEvaluator {
	
	/**
	 * Assesses the compensations that are derived from a list of violations.
	 * 
	 * @param agreement agreement being evaluated.
	 * @param term of the agreement being evaluated.
	 * @param violations detected in the service level evaluation.
	 * @param now the evaluation period ends at <code>now</code>.
	 * @return list of compensations.
	 */
	List<? extends ICompensation> evaluate(
			IAgreement agreement, IGuaranteeTerm term, List<IViolation> violations, Date now);
}