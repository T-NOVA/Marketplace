package eu.atos.sla.datamodel;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;

import eu.atos.sla.datamodel.bean.GuaranteeTerm;

public class GuaranteeTermTest {

	@Ignore
	@Test
	public void testPojo() {

		@SuppressWarnings("unused")
		String consumerUuid = UUID.randomUUID().toString();

		GuaranteeTerm guaranteeTerm = new GuaranteeTerm();
		
		guaranteeTerm.setName("guarantee term name");
		guaranteeTerm.setServiceName("service Name");
		

		assertEquals("guarantee term name", guaranteeTerm.getName());
		assertEquals("service Name", guaranteeTerm.getServiceName());
		

	}
}