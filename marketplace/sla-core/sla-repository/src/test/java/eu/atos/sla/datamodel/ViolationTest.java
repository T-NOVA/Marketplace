package eu.atos.sla.datamodel;

import static org.junit.Assert.assertEquals;
import eu.atos.sla.datamodel.bean.Violation;

public class ViolationTest {

	//@Test
	public void testPojo() {


		Violation violation = new Violation();
		violation.setServiceName("service name");

		assertEquals("service name", violation.getServiceName());

	}
}
