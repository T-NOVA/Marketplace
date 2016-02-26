package eu.atos.sla.enforcement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IBusinessValueList;
import eu.atos.sla.datamodel.IPolicy;
import eu.atos.sla.datamodel.IViolation;
import eu.atos.sla.datamodel.ICompensationDefinition.IPenaltyDefinition;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.datamodel.IProvider;
import eu.atos.sla.datamodel.bean.Agreement;
import eu.atos.sla.datamodel.bean.BusinessValueList;
import eu.atos.sla.datamodel.bean.GuaranteeTerm;
import eu.atos.sla.datamodel.bean.Policy;
import eu.atos.sla.datamodel.bean.Provider;
import eu.atos.sla.datamodel.bean.Violation;

public class TestAgreementFactory {
	
	public static IAgreement newAgreement(List<IGuaranteeTerm> terms) {
		
		return newAgreement(
				UUID.randomUUID().toString(),
				new Provider(),
				"",
				terms
				);
	}
	
	public static IAgreement newAgreement(
			String agreementId, 
			IProvider provider, 
			String consumer, 
			List<IGuaranteeTerm> terms) {
		
		IAgreement result = new Agreement();
		result.setAgreementId(agreementId);
		result.setProvider(provider);
		result.setConsumer(consumer);
		result.setGuaranteeTerms(terms);
		
		return result;
	}
	
	public static IGuaranteeTerm newGuaranteeTerm(String kpiName, String constraint) {

		return newGuaranteeTerm(kpiName, constraint, Collections.<IPenaltyDefinition>emptyList());
	}

	public static IGuaranteeTerm newGuaranteeTerm(
			String kpiName, String constraint, List<IPenaltyDefinition> penalties) {
		
		GuaranteeTerm t = new GuaranteeTerm();
		t.setName(kpiName);
		t.setKpiName(kpiName);
		t.setServiceLevel(constraint);
		t.setPolicies(
			Arrays.<IPolicy>asList(new Policy(1, new Date(0)))
		);
		t.setViolations(new ArrayList<IViolation>());
		
		IBusinessValueList businessValueList = new BusinessValueList(0, penalties);
		t.setBusinessValueList(businessValueList);
		return t;
	}

	public static IViolation newViolation(IAgreement agreement, IGuaranteeTerm term, IPolicy policy) {
		
		return newViolation(agreement, term, policy, new Date());
	}
	
	public static IViolation newViolation(IAgreement agreement, IGuaranteeTerm term, IPolicy policy, Date datetime) {
		
		IViolation result = new Violation(agreement, term, policy, "", "", datetime);
		return result;
	}
}
