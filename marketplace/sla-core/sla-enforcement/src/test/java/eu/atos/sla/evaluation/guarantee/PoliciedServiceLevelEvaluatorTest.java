package eu.atos.sla.evaluation.guarantee;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import eu.atos.sla.enforcement.TestAgreementFactory;
import org.junit.Before;
import org.junit.Test;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IBreach;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.IPolicy;
import eu.atos.sla.datamodel.IViolation;
import eu.atos.sla.datamodel.bean.Agreement;
import eu.atos.sla.datamodel.bean.GuaranteeTerm;
import eu.atos.sla.datamodel.bean.Policy;
import eu.atos.sla.evaluation.constraint.IConstraintEvaluator;
import eu.atos.sla.evaluation.constraint.simple.SimpleConstraintEvaluator;
import eu.atos.sla.evaluation.guarantee.PoliciedServiceLevelEvaluator.ActualValueBuilder;
import eu.atos.sla.monitoring.IMonitoringMetric;

//@RunWith(SpringJUnit4ClassRunner.class)
public class PoliciedServiceLevelEvaluatorTest {

	private static final int THRESHOLD = 100;
    Date now;       /* now is exactly 1s after last metric ( = t + 1) */
	Date[] dates;
	String[] values;
	String constraint;
	IAgreement contract;
	IGuaranteeTerm term;
	PoliciedServiceLevelEvaluator evaluator;
	IBreachRepository breachRepository;
	IViolationRepository violationRepository;
	List<IMonitoringMetric> allMetrics;
	ActualValueBuilder actualValueBuilder = new ActualValueBuilder();
	String kpiName;
	List<IViolation> violations;
	
	@Before
	public void setUp() throws Exception {
        now = new Date(61 * 1000);
		kpiName = "LATENCY";
		constraint = kpiName + " LT " + THRESHOLD;			/* Must agree with isBreach() */
		
		IConstraintEvaluator metricsValidator = new SimpleConstraintEvaluator();
		evaluator = new PoliciedServiceLevelEvaluator();
		evaluator.setConstraintEvaluator(metricsValidator);
		
		contract = new Agreement();
		contract.setAgreementId("agreement-test");
		term = newGuaranteeTerm(kpiName, constraint); 
		violations = Collections.<IViolation>emptyList();
		violationRepository = new SimpleBusinessValuesEvaluatorTest.DummyViolationRepository(violations);
		evaluator.setViolationRepository(violationRepository);
	}
	
    
	/**
     * Common setup for tests with policies (after setUp has run).
     * 
     * Values is the temporal series of metrics (one per second).
     * 
     * <code>t</code> is the last measure. <code>now</code> is <code>t+1</code>.
     * <pre>
     *                  |-> new values
     *  -7 -6 -5 -4  -3  -2 -1  0  t
     * 102 99 99 99 101 100 99 99  metric
     *  B            B   B         breaches
     *  </pre>
		 */
    private List<IMonitoringMetric> setUpWithPolicies(String[] valuesToAdd, List<IViolation> violations) {
        String[] oldValues = new String[] { "102", "99", "99", "99", "101" };
        String[] newValues = new String[] { "100", "99", "99" };
        if (valuesToAdd != null) {
            newValues = join(newValues, valuesToAdd);
        }
        return setUpMetrics(oldValues, newValues, violations);
    }

    private List<IMonitoringMetric> setUpMetrics(String[] oldValues, String[] newValues, List<IViolation> violations) {
        this.values = join(oldValues, newValues);
		TestMetricsGenerator metricsGenerator = new TestMetricsGenerator(now, values);
		allMetrics = metricsGenerator.getMetrics(kpiName);
		
        int splitIndex = oldValues.length;
		List<IMonitoringMetric> newMetrics = allMetrics.subList(splitIndex, allMetrics.size());
		List<IMonitoringMetric> oldMetrics = allMetrics.subList(0, splitIndex);
		breachRepository = new DummyBreachRepository(constraint, oldMetrics);
		evaluator.setBreachRepository(breachRepository);
		
        if (violations != null) {
            violationRepository = new SimpleBusinessValuesEvaluatorTest.DummyViolationRepository(violations);
            evaluator.setViolationRepository(violationRepository);
        }
		return newMetrics;
	}

	@Test
	public void testEvaluateWithoutPolicy() {

		System.out.println("---testEvaluateWithoutPolicy()---");
		String kpiName = "UPTIME";
		values = new String[] { "99", "100", "98", "101" };
		TestMetricsGenerator metricsGenerator = new TestMetricsGenerator(now, values);
		List<IMonitoringMetric> metrics = metricsGenerator.getMetrics(kpiName);
		
		breachRepository = new DummyBreachRepository(constraint);
		evaluator.setBreachRepository(breachRepository);
		evaluator.evaluate(contract, term, metrics, now);
		
		List<IViolation> violations = evaluator.evaluate(contract, term, metrics, now);
		
		assertEquals(2, violations.size());
		assertViolations(violations, metrics.get(1), metrics.get(3));
	}

	@Test
	public void testGetViolationsWithPolicy1() {
		IPolicy policy;
		System.out.println("---testEvaluateWithPolicy1()---");

		/*
         * t-2, t-3 are breaches but are separated more than policy interval -> 0 violations 
		 */
        List<IMonitoringMetric> metrics = setUpWithPolicies(null, null);

        policy = newPolicy("LATENCY", 2, new Date(900));
        term.setPolicies(Arrays.asList(new IPolicy[] { policy }));

		List<IViolation> violations = evaluator.evaluate(contract, term, metrics, now);

		assertEquals(0, violations.size());
	}
	
	@Test
    public void testNewBreachesAreSaved() {

        List<IMonitoringMetric> metrics = setUpWithPolicies(null, null);
        IPolicy policy = newPolicy("LATENCY", 2, new Date(3000));
        term.setPolicies(Arrays.asList(new IPolicy[] { policy }));

        violations = evaluator.evaluate(contract, term, metrics, now);
        assertEquals(3, breachRepository.getBreachesByTimeRange(contract, kpiName, new Date(0), now).size());
        assertEquals(1, breachRepository.getBreachesByTimeRange(
                contract, kpiName, new Date(now.getTime() - 3500), now).size());
    }

    @Test
	public void testGetViolationsWithPolicy2() {
		IPolicy policy;

		System.out.println("---testEvaluateWithPolicy2()---");
		/*
		 * t-2, t-3 are breaches -> 1 violation
		 */
        List<IMonitoringMetric> metrics = setUpWithPolicies(null, null);

        policy = newPolicy("LATENCY", 2, new Date(4000));
        term.setPolicies(Arrays.asList(new IPolicy[] { policy }));

		List<IViolation> violations = evaluator.evaluate(contract, term, metrics, now);

		assertEquals(1, violations.size());
		assertViolations(violations, allMetrics.get(4), allMetrics.get(5));
	}
	
	@Test
	public void testGetViolationsWithPolicy3() {
		IPolicy policy;

		System.out.println("---testEvaluateWithPolicy3()---");
		/*
		 * t-2, t-3 are breaches -> 0 violation
		 */
        List<IMonitoringMetric> metrics = setUpWithPolicies(null, null);

        policy = newPolicy("LATENCY", 3, new Date(4000));
        term.setPolicies(Arrays.asList(new IPolicy[] { policy }));

		List<IViolation> violations = evaluator.evaluate(contract, term, metrics, now);

		assertEquals(0, violations.size());

	}
	
	@Test
	public void testGetViolationsWithPolicy4() {
		IPolicy policy;

		System.out.println("---testEvaluateWithPolicy4()---");
		/*
		 * t-2, t-3, t-7 are breaches -> 1 violation
		 */
        List<IMonitoringMetric> metrics = setUpWithPolicies(null, null);

		policy = newPolicy("LATENCY", 3, new Date(10000));
		term.setPolicies(Arrays.asList(new IPolicy[] { policy }));

		List<IViolation> violations = evaluator.evaluate(contract, term, metrics, now);

		assertEquals(1, violations.size());
		assertViolations(violations, allMetrics.get(0), allMetrics.get(4), allMetrics.get(5));
	}

	@Test
	public void testGetViolationsWithPolicy5() {

		System.out.println("---testEvaluateWithPolicy5()---");
		/*
         * t-2, t-3 are breaches -> 1 violation (2nd policy)
		 */
        List<IMonitoringMetric> metrics = setUpWithPolicies(null, null);

        term.setPolicies(Arrays.asList(new IPolicy[] { 
                newPolicy("LATENCY", 3, new Date(4000)),
				newPolicy("LATENCY", 2, new Date(3000))
		}));

		List<IViolation> violations = evaluator.evaluate(contract, term, metrics, now);

		assertEquals(1, violations.size());
        assertViolations(violations, t(-3), t(-2));
        assertEquals(3000, violations.get(0).getPolicy().getTimeInterval().getTime());
	}

	@Test
	public void testGetViolationsWithPolicy6() {

		System.out.println("---testEvaluateWithPolicy6()---");
		/*
         * t-2, t-3, t-7 are breaches -> 2 violations (1 per policy)
		 */
        List<IMonitoringMetric> metrics = setUpWithPolicies(null, null);

		term.setPolicies(Arrays.asList(new IPolicy[] { 
				newPolicy("LATENCY", 3, new Date(10000)),
                newPolicy("LATENCY", 2, new Date(4000))
		}));

		List<IViolation> violations = evaluator.evaluate(contract, term, metrics, now);

		assertEquals(2, violations.size());
		assertEquals(10000, violations.get(0).getPolicy().getTimeInterval().getTime());
        assertEquals(4000, violations.get(1).getPolicy().getTimeInterval().getTime());
		assertViolations(
				Collections.singletonList(violations.get(0)), 
                t(-7), t(-3), t(-2) 
		);
		assertViolations(
				Collections.singletonList(violations.get(1)), 
                t(-3), t(-2)
		);
	}

    @Test
    public void testPolicyWithExistingViolation() {

        /*
         * This test is equal to testPolicy4, but with an existing violation.
         *
         * t-2, t-3, t-7 are breaches
         * t-6 is violation -> 0 violations
         */
        IPolicy policy = newPolicy("LATENCY", 3, new Date(10000));
        term.setPolicies(Arrays.asList(new IPolicy[] { policy }));
        Date violationDate = substract(now, new Date(7000));
        List<IViolation> existingViolations = Arrays.asList(
                TestAgreementFactory.newViolation(contract, term, policy, violationDate)
        );
        List<IMonitoringMetric> metrics = setUpWithPolicies(null, existingViolations);

        List<IViolation> violations = evaluator.evaluate(contract, term, metrics, now);

        assertEquals(0, violations.size());
    }

    @Test
    public void testPolicyWithExistingOldViolation() {

        /*
         * This test is equal to testPolicy4, but with an existing violation, old to affect the evaluation.
         *
         * t-2, t-3, t-7 are breaches
         * t-11 is violation (before the policy interval) -> 1 violations
         */
        IPolicy policy = newPolicy("LATENCY", 3, new Date(10000));
        Date violationDate = new Date(now.getTime() - policy.getTimeInterval().getTime() - 1000);
        List<IViolation> existingViolations = Arrays.asList(
                TestAgreementFactory.newViolation(contract, term, policy, violationDate)
        );
        
        List<IMonitoringMetric> metrics = setUpWithPolicies(null, existingViolations);
        term.setPolicies(Arrays.asList(new IPolicy[] { policy }));

        List<IViolation> violations = evaluator.evaluate(contract, term, metrics, now);

        assertEquals(1, violations.size());
        assertViolations(violations, t(-7), t(-3), t(-2));
    }
    
    @Test
    public void testPolicyWithMultipleViolationsRaised() {
        /*
         * This test adds a breach at the end of the usual breaches.
         * t, t-3, t-4, t-8 are breaches -> 2 violations (t-4, t)
         */
        List<IMonitoringMetric> metrics = setUpWithPolicies(new String[] { "103" }, null);
        IPolicy policy = newPolicy("LATENCY", 2, new Date(10000));
        term.setPolicies(Arrays.asList(new IPolicy[] { policy }));

        List<IViolation> violations = evaluator.evaluate(contract, term, metrics, now);

        assertEquals(2, violations.size());
        assertViolations(violations, t(-8), t(-4), t(-3), t(0));
    }
    
    @Test
    public void testGapBetweenBreaches1() {
        /*
         * t-5, t-4 are the only breaches that generate a violation. The rest satisfy the policy. (t-4, t-3) should
         * not generate a violation, since there was a violation in t-4.
         * -> 1 violation (t-5, t-4)
         */
        String[] oldValues = new String[] { "99", "99", "100" };
        String[] newValues = new String[] { "99", "101", "99", "99", "102", "103", "104", "99", "99", "105" };
        List<IMonitoringMetric> newMetrics = setUpMetrics(oldValues, newValues, null);

        IPolicy policy = newPolicy("LATENCY", 2, new Date(1900));
        term.setPolicies(Arrays.asList(new IPolicy[] { policy }));

        List<IViolation> violations = evaluator.evaluate(contract, term, newMetrics, now);

        assertEquals(1, violations.size());
        assertViolations(violations, t(-5), t(-4));
    }

	/**
	 * Assert that the raised violations match with the expected violations.
	 * 
	 * Assumes that both lists have the same order.
	 */
	private void assertViolations(List<IViolation> violations, IMonitoringMetric... expectedViolatedMetrics) {
		
		for (int i = 0; i < violations.size(); i++) {
            IViolation v = violations.get(i);
            int count = v.getBreaches() != null? v.getBreaches().size() : 1;
            IMonitoringMetric m = expectedViolatedMetrics[(i + 1) * count - 1];
			
			assertEquals(m.getDate(), v.getDatetime());
			assertEquals(m.getMetricValue(), v.getActualValue());

            for (int j = 0; v.getBreaches() != null && j < v.getBreaches().size(); j++) {
                IBreach b = v.getBreaches().get(j);
                m = expectedViolatedMetrics[i * count + j];
                assertEquals(m.getDate(), b.getDatetime());
                assertEquals(m.getMetricValue(), b.getValue());
            }
		}
	}
	
    /**
     * Returns the datum at position "t" in time of allMetrics array.
     * 
     * 0 corresponds to the item at length-1 position (last)
     * -1 correspond to the item at length-2 position
     * ...
     * -(allMetrics.length-1) corresponds to the item at 0 position
     */
    private IMonitoringMetric t(int offset) {
        
        return allMetrics.get(allMetrics.size() - 1 + offset);
    }

    private IGuaranteeTerm newGuaranteeTerm(String kpiName, String constraint) {
		
		GuaranteeTerm t = new GuaranteeTerm();
		t.setKpiName(kpiName);
		t.setServiceLevel(constraint);
		
		return t;
	}
	
	private IPolicy newPolicy(String kpiName, int count, Date timeInterval) {
		
		Policy p = new Policy();
		p.setVariable(kpiName);
		p.setCount(count);
		p.setTimeInterval(timeInterval);
		
		return p;
	}
	
    private Date substract(Date d1, Date d2) {
        
        return new Date(d1.getTime() - d2.getTime());
    }
    
    private String[] join(String[] arr1, String[] arr2) {
        List<String> aux = new ArrayList<String>();
        aux.addAll(Arrays.asList(arr1));
        aux.addAll(Arrays.asList(arr2));
        arr1 = aux.toArray(arr1);
        return arr1;
    }

	static class MonitoringMetric implements IMonitoringMetric {

		private String metricKey;
		private String value;
		private Date date;
		
		
		public MonitoringMetric(String metricKey, String value, Date date) {
			super();
			this.metricKey = metricKey;
			this.value = value;
			this.date = date;
		}

		@Override
		public String getMetricKey() {
			return metricKey;
		}

		@Override
		public String getMetricValue() {
			return value;
		}

		@Override
		public Date getDate() {
			return date;
		}
		
        @Override
        public String toString() {
            return String.format("MonitoringMetric[key=%s,value=%s,date=%s]", metricKey, value, date);
        }
	}
}
