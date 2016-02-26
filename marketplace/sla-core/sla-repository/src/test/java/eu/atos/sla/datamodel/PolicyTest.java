package eu.atos.sla.datamodel;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import eu.atos.sla.datamodel.bean.Policy;

public class PolicyTest {

	@Ignore
	@Test
	public void testPojo() {

		Policy policy = new Policy();
		policy.setCount(new Integer(2323));
		policy.setTimeInterval(new Date(1234));

		assertEquals(new Integer(2323), policy.getCount());
		assertEquals(new Date(1234), policy.getTimeInterval());

	}
}
