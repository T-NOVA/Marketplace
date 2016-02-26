package eu.atos.sla.notification;

import java.util.Map;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.evaluation.guarantee.GuaranteeTermEvaluator.GuaranteeTermEvaluationResult;



public interface INotifierManager   {

	public void addToBeNotified(IAgreement agreement, Map<IGuaranteeTerm, GuaranteeTermEvaluationResult> evaluationResult) ;

}