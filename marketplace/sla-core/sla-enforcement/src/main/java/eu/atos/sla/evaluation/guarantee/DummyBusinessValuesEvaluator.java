package eu.atos.sla.evaluation.guarantee;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.ICompensation;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.IViolation;

/**
 * Business evaluator that does nothing.
 * 
 * @author rsosa
 */
public class DummyBusinessValuesEvaluator implements IBusinessValuesEvaluator {

	@Override
	public List<ICompensation> evaluate(IAgreement agreement,
			IGuaranteeTerm term, List<IViolation> violations, Date now) {

		return Collections.emptyList();
	}
}