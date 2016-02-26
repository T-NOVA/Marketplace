package eu.atos.sla.notification;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.evaluation.guarantee.GuaranteeTermEvaluator.GuaranteeTermEvaluationResult;

public class DummyEnforcementNotifier implements IAgreementEnforcementNotifier {
	private static Logger logger = LoggerFactory.getLogger(DummyEnforcementNotifier.class);

	@Override
	public void onFinishEvaluation(IAgreement agreement,
			Map<IGuaranteeTerm, GuaranteeTermEvaluationResult>  guaranteeTermEvaluationMap) {
		logger.debug("Notifying onFinishEvaluation {}", agreement.getId());
		for (Entry<IGuaranteeTerm, GuaranteeTermEvaluationResult> e:guaranteeTermEvaluationMap.entrySet()) {
			IGuaranteeTerm gt = e.getKey();
			GuaranteeTermEvaluationResult gtresult = e.getValue();
			logger.debug("Notifying onFinishEvaluation GuaranteeTerm:{} Num violations:{} Num compensations:{} ", gt.getId(), gtresult.getViolations().size(), gtresult.getCompensations().size());
		}
		logger.debug("  onFinishEvaluation", agreement.getId());
	}

}
