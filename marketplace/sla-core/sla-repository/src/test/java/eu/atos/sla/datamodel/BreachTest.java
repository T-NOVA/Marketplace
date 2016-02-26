package eu.atos.sla.datamodel;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;

import eu.atos.sla.datamodel.bean.Breach;

public class BreachTest {

	@Ignore
	@Test
	public void testPojo() {

		String contractUUID = UUID.randomUUID().toString();

		Breach breach = new Breach();

		breach.setAgreementUuid(contractUUID);

		assertEquals(contractUUID, breach.getAgreementUuid());

	}
}