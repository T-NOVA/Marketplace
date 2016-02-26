package eu.atos.sla.service.types;

import eu.atos.sla.parser.data.wsag.IAgreement;


/**
 * 
 * @author Elena Garrido
 */

public class AgreementParam {
	IAgreement agreement;
	String originalSerialzedAgreement;
	public IAgreement getAgreement() {
		return agreement;
	}
	public void setAgreement(IAgreement agreement) {
		this.agreement = agreement;
	}
	public String getOriginalSerialzedAgreement() {
		return originalSerialzedAgreement;
	}
	public void setOriginalSerialzedAgreement(String originalSerialzedAgreement) {
		this.originalSerialzedAgreement = originalSerialzedAgreement;
	}
}
