package eu.atos.sla.service.jpa;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.atos.sla.dao.IAgreementDAO;
import eu.atos.sla.dao.IEnforcementJobDAO;
import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IEnforcementJob;
import eu.atos.sla.datamodel.IProvider;
import eu.atos.sla.datamodel.bean.Agreement;
import eu.atos.sla.datamodel.bean.EnforcementJob;
import eu.atos.sla.datamodel.bean.Provider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/sla-repository-db-JPA-test-context.xml")
public class EnforcementJobDAOJpaTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	IEnforcementJobDAO enforcementJobDAO;

	@Autowired
	IAgreementDAO agreementDAO;

	@Test
	public void notNull() {
		if (enforcementJobDAO == null)
			fail();
	}

	@Test
	public void save() {

		IAgreement agreementFromDatabase= new Agreement();

		String uuid = UUID.randomUUID().toString();

		IAgreement agreement = new Agreement();
		agreement.setAgreementId(uuid);
		agreement.setConsumer("consumer1");
		
		IProvider provider = new Provider();
		provider.setUuid(UUID.randomUUID().toString());
		provider.setName("provider");
			
		agreement.setProvider(provider);
		agreement.setText("<Agreement>...</Agreement>");

		try {
			agreementFromDatabase= agreementDAO.save(agreement);

		} catch (Exception e) {
			
		}

		
		Date dateTime = new Date(12L);

		IEnforcementJob enforcementJob = new EnforcementJob();
		enforcementJob.setAgreement(agreementFromDatabase);
		enforcementJob.setEnabled(false);
		enforcementJob.setLastExecuted(dateTime);

		@SuppressWarnings("unused")
		IEnforcementJob saved = new EnforcementJob();
		try {
			saved = enforcementJobDAO.save(enforcementJob);
		} catch (Exception e) {
		
		}

	}
}
