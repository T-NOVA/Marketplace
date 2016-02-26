package eu.atos.sla.service.jpa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eu.atos.sla.dao.IAgreementDAO;
import eu.atos.sla.dao.IBreachDAO;
import eu.atos.sla.dao.IGuaranteeTermDAO;
import eu.atos.sla.dao.IPolicyDAO;
import eu.atos.sla.dao.ITemplateDAO;
import eu.atos.sla.dao.IViolationDAO;
import eu.atos.sla.datamodel.bean.Agreement;

public class MysqlJpaIT {

	@SuppressWarnings({ "resource", "unused" })
	public static void main(String args[]) throws InterruptedException {
		// Load Spring configuration
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"/sla-repository-db-JPA-test-context.xml");
		IAgreementDAO agreementDAO = (IAgreementDAO) context
				.getBean("AgreementService");
		IBreachDAO breachDAO = (IBreachDAO) context.getBean("BreachService");
		IGuaranteeTermDAO guaranteeTerm = (IGuaranteeTermDAO) context
				.getBean("GuaranteeTermService");

		IPolicyDAO slaPolicyDAO = (IPolicyDAO) context
				.getBean("SLAPolicyService");
		ITemplateDAO templateDAO = (ITemplateDAO) context
				.getBean("TemplateService");
		IViolationDAO violationDAO = (IViolationDAO) context
				.getBean("ViolationService");

		Agreement agreement = new Agreement();
		agreement.setId(1L);

		Agreement agreementSaved = null;

		try {
			agreementDAO.save(agreement);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if (agreementSaved != null) {
			System.out.println("IMPOSIBLE TO SAVE AGREEMENT!!!!");
			Thread.sleep(600000l);
		}
	}
}
