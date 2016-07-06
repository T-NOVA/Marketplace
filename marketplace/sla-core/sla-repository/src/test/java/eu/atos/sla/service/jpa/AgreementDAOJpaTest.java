package eu.atos.sla.service.jpa;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.atos.sla.dao.IAgreementDAO;
import eu.atos.sla.dao.IProviderDAO;
import eu.atos.sla.dao.ITemplateDAO;
import eu.atos.sla.dao.jpa.AgreementDAOJpa;
import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.IPolicy;
import eu.atos.sla.datamodel.IViolation;
import eu.atos.sla.datamodel.bean.Agreement;
import eu.atos.sla.datamodel.bean.GuaranteeTerm;
import eu.atos.sla.datamodel.bean.Policy;
import eu.atos.sla.datamodel.bean.Provider;
import eu.atos.sla.datamodel.bean.Template;
import eu.atos.sla.datamodel.bean.Violation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/sla-repository-db-JPA-test-context.xml")
public class AgreementDAOJpaTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	IProviderDAO providerDAO;
	
	@Autowired
	IAgreementDAO agreementDAO;

	@Autowired
	ITemplateDAO templateDAO;
	
	private static boolean setupDone;
	
	private Provider provider;
	private Template template;
	
	private void setUp() {
		if (setupDone) {
			return;
		}
		provider = new Provider(null, UUID.randomUUID().toString(), "Provider2");
		providerDAO.save(provider);

		String templateUuid = UUID.randomUUID().toString();
		template = new Template();
		template.setText("Template name 1");
		template.setUuid(templateUuid);
		template.setProvider(provider);
		
		templateDAO.save(template);
	}
	
	@Test
	public void notNull() {
		if (agreementDAO == null)
			fail();
	}

	
	
	@Test
	public void getById() {

		String uuid = UUID.randomUUID().toString();

		StringBuilder agreementText = new StringBuilder();

		agreementText
				.append("<Agreement xmlns=\"http://www.ggf.org/namespaces/ws-agreement\" AgreementId=\""
						+ uuid + "\">\n");
		agreementText.append("  <Name>ExampleAgreement</name>\n");
		agreementText.append("  <Context>\n");
		agreementText
				.append("   <AggreementInitiator>RandomClient</AgreementInitiator>\n");
		agreementText
				.append("   <AgreementResponder>Provider02</AgreementResponder>\n");
		agreementText
				.append("   <ServiceProvider>AgreementResponder</ServiceProvider>\n");
		agreementText
				.append("   <ExpirationTime>2014-03-07-1200</ExpirationTime>\n");
		agreementText
				.append("   <TemplateId>contract-template-2007-12-04</<TemplateId>>\n");
		agreementText.append("  </Context>\n");
		agreementText.append("</Agreement>\n");

		IAgreement agreement = new Agreement();
		agreement.setAgreementId(uuid);
		agreement.setConsumer("consumer2");
		agreement.setProvider(new Provider(null, UUID.randomUUID().toString(),
				"provider3"));
		agreement.setText(agreementText.toString());

		@SuppressWarnings("unused")
		IAgreement agreementSaved = new Agreement();
		try {
			agreementSaved = agreementDAO.save(agreement);
		} catch (Exception e) {
			
		}

		IAgreement nullAgreement = agreementDAO.getById(new Long(30000));
		assertEquals(null, nullAgreement);
	}

	//@Test
	public void getByAgreementId() {

		StringBuilder agreementText = new StringBuilder();

		String uuid = UUID.randomUUID().toString();

		agreementText
				.append("<Agreement xmlns=\"http://www.ggf.org/namespaces/ws-agreement\" AgreementId=\""
						+ uuid + "\">\n");
		agreementText.append("  <Name>ExampleAgreement2</name>\n");
		agreementText.append("  <Context>\n");
		agreementText
				.append("   <AggreementInitiator>RandomClient2</AgreementInitiator>\n");
		agreementText
				.append("   <AgreementResponder>Provider02</AgreementResponder>\n");
		agreementText
				.append("   <ServiceProvider>AgreementResponder2</ServiceProvider>\n");
		agreementText
				.append("   <ExpirationTime>2014-03-07-1200</ExpirationTime>\n");
		agreementText
				.append("   <TemplateId>contract-template-2007-12-04</<TemplateId>>\n");
		agreementText.append("  </Context>\n");
		agreementText.append("</Agreement>\n");

		IAgreement agreement = new Agreement();
		agreement.setAgreementId(uuid);
		agreement.setConsumer("consumer jose");
		agreement.setProvider(new Provider(null, UUID.randomUUID().toString(),
				"provider pepito"));
		agreement.setText(agreementText.toString());

		try {
			agreementDAO.save(agreement);
		} catch (Exception e) {
			assertEquals(e, agreement);
		}

		IAgreement nullAgreement = agreementDAO.getById(new Long(30000));
		assertEquals(null, nullAgreement);
	}

	@Test
	public void save() {

		setUp();
		
		StringBuilder agreementText = new StringBuilder();

		String agreementId = UUID.randomUUID().toString();

		agreementText
				.append("<Agreement xmlns=\"http://www.ggf.org/namespaces/ws-agreement\" AgreementId=\""
						+ agreementId + "\">\n");
		agreementText.append("  <Name>ExampleAgreement</name>\n");
		agreementText.append("  <Context>\n");
		agreementText
				.append("   <AggreementInitiator>RandomClient</AgreementInitiator>\n");
		agreementText
				.append("   <AgreementResponder>Provider01</AgreementResponder>\n");
		agreementText
				.append("   <ServiceProvider>AgreementResponder</ServiceProvider>\n");
		agreementText
				.append("   <ExpirationTime>2014-03-07-1200</ExpirationTime>\n");
		agreementText
				.append("   <TemplateId>contract-template-2007-12-04</<TemplateId>>\n");
		agreementText.append("  </Context>\n");
		agreementText.append("</Agreement>\n");

		eu.atos.sla.datamodel.IAgreement.AgreementStatus status = eu.atos.sla.datamodel.IAgreement.AgreementStatus.PENDING;

		// Guarantee terms

		IGuaranteeTerm guaranteeTerm = new GuaranteeTerm();
		guaranteeTerm.setName("guarantee term name");
		guaranteeTerm.setServiceName("service Name");

		List<IGuaranteeTerm> guaranteeTerms = new ArrayList<IGuaranteeTerm>();
		guaranteeTerms.add(guaranteeTerm);

		IAgreement agreement = new Agreement();
		agreement.setAgreementId(agreementId);
		agreement.setConsumer("Consumer2");
		agreement.setProvider(provider);
		agreement.setStatus(status);
		agreement.setTemplate(template);
		agreement.setText(agreementText.toString());
		agreement.setGuaranteeTerms(guaranteeTerms);

		IViolation violation = new Violation();

		violation.setContractUuid(agreementId);
		violation.setActualValue("8.0");
		violation.setExpectedValue("5.0");
		@SuppressWarnings("unused")
		IAgreement agreementSaved = new Agreement();
		try {
			agreementSaved = agreementDAO.save(agreement);

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}
	
	@Test
	public void testSaveWindows() throws FileNotFoundException {
		setUp();
		
		String text = readFile("/samples/test_parse_agreement.xml");
		/*
		 * Now build agreement entities by hand
		 */
		Agreement agreement = new Agreement();
		
		agreement.setAgreementId(UUID.randomUUID().toString());
		agreement.setTemplate(template);
		agreement.setConsumer("consumer2");
		agreement.setProvider(provider);
		agreement.setText(text);

		// Guarantee terms

		IGuaranteeTerm guaranteeTerm = new GuaranteeTerm();
		guaranteeTerm.setName("guarantee term name");
		guaranteeTerm.setServiceName("service Name");
		guaranteeTerm.setServiceLevel("responsetime LT 200");
		guaranteeTerm.setKpiName("fast_response_time");
		
		Policy[] policies = new Policy[] {
			new Policy(2, new Date(120 * 1000)),
			new Policy(3, new Date(3600 * 1000))
		};
		guaranteeTerm.setPolicies(Arrays.<IPolicy>asList(policies));
		

		List<IGuaranteeTerm> guaranteeTerms = new ArrayList<IGuaranteeTerm>();
		guaranteeTerms.add(guaranteeTerm);
		agreement.setGuaranteeTerms(guaranteeTerms);
		
		agreementDAO.save(agreement);
		assertNotNull(agreement.getId());

		((AgreementDAOJpa)agreementDAO).getEntityManager().detach(agreement);
		
		agreement = (Agreement) agreementDAO.getByAgreementId(agreement.getAgreementId());
		guaranteeTerm = agreement.getGuaranteeTerms().get(0);
		assertNotNull(guaranteeTerm.getPolicies());
		assertEquals(2, guaranteeTerm.getPolicies().size());
		for (IPolicy p : guaranteeTerm.getPolicies()) {
			System.out.println(p.getCount() + " " + p.getTimeInterval());
		}

	}
	
	private String readFile(String path) throws FileNotFoundException {
		URL url = this.getClass().getResource(path);
		if (url == null) {
			throw new NullPointerException("Resource" + path + " not found");
		}
		File f = new File(url.getFile());
		Scanner s = new Scanner(new FileReader(f));
		String result = s.next();
		s.close();
		
		return result;
	}
	
}
