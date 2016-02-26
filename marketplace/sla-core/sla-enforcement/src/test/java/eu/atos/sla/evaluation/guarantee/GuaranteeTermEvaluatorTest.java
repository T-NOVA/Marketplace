package eu.atos.sla.evaluation.guarantee;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.bean.Agreement;
import eu.atos.sla.datamodel.bean.GuaranteeTerm;
import eu.atos.sla.evaluation.constraint.IConstraintEvaluator;
import eu.atos.sla.evaluation.constraint.simple.SimpleConstraintEvaluator;
import eu.atos.sla.evaluation.guarantee.DummyBusinessValuesEvaluator;
import eu.atos.sla.evaluation.guarantee.GuaranteeTermEvaluator;
import eu.atos.sla.evaluation.guarantee.IBusinessValuesEvaluator;
import eu.atos.sla.evaluation.guarantee.PoliciedServiceLevelEvaluator;
import eu.atos.sla.evaluation.guarantee.GuaranteeTermEvaluator.GuaranteeTermEvaluationResult;
import eu.atos.sla.monitoring.IMonitoringMetric;

public class GuaranteeTermEvaluatorTest {

	GuaranteeTermEvaluator evaluator;
	Date now;
	Date[] dates;
	String[] values;
	List<IMonitoringMetric> allMetrics;
	String constraint;
	IAgreement contract;
	IGuaranteeTerm term;
	String kpiName;
	
	@Before
	public void setUp() throws Exception {
		now = new Date();
		
		kpiName = "LATENCY";
		constraint = kpiName + " LT 100";

		contract = new Agreement();
		contract.setAgreementId("agreement-test");
		term = newGuaranteeTerm(kpiName, constraint);

		values = new String[] { "102", "99", "99", "99", "101", "100", "99", "99" };
		TestMetricsGenerator metricsGenerator = new TestMetricsGenerator(now, values);
		allMetrics = metricsGenerator.getMetrics(kpiName);
		
		IConstraintEvaluator constraintEval = new SimpleConstraintEvaluator();
		evaluator = new GuaranteeTermEvaluator();
		PoliciedServiceLevelEvaluator slEval = 
				new PoliciedServiceLevelEvaluator();
		slEval.setConstraintEvaluator(constraintEval);
		slEval.setBreachRepository(new DummyBreachRepository(constraint));
		IBusinessValuesEvaluator bEval = new DummyBusinessValuesEvaluator();
		evaluator.setServiceLevelEvaluator(slEval);
		evaluator.setBusinessEvaluator(bEval);
		
	}

//	@Test
	public void testEvaluate() {
		/*
		 * simple test, as all the hard work is done in SLEvaluator and BusinessEvaluator
		 */
		
		GuaranteeTermEvaluationResult result = evaluator.evaluate(contract, term, allMetrics, now);
		assertEquals(3, result.getViolations().size());
	}

	private IGuaranteeTerm newGuaranteeTerm(String kpiName, String constraint) {
		
		GuaranteeTerm t = new GuaranteeTerm();
		t.setKpiName(kpiName);
		t.setServiceLevel(constraint);
		
		return t;
	}
}
