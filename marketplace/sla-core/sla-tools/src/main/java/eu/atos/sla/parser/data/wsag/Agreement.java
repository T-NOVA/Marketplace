package eu.atos.sla.parser.data.wsag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * A POJO Object that stores all the information from a Agreement
 * 
 * @author Pedro Rey - Atos
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Agreement")
public class Agreement implements IAgreement {

	@XmlAttribute(name = "AgreementId")
	private String agreementId;
	@XmlElement(name = "Name")
	private String name;
	@XmlElement(name = "Context")
	private Context context;
	@XmlElement(name = "Terms")
	private Terms terms;

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IContext getContext() {
		return context;
	}

	public void setContext(IContext context) {
		this.context = (Context)context;
	}

	public ITerms getTerms() {
		return terms;
	}

	public void setTerms(ITerms terms) {
		this.terms = (Terms)terms;
	}

	@Override
	public String toString() {

		return "Agreement[id='" + agreementId + "']";
	}
}