package eu.atos.sla.parser.data.wsag;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eu.atos.sla.parser.DateTimeDeserializerJSON;
import eu.atos.sla.parser.DateTimeSerializerJSON;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Context")
public class Context implements IContext{

	/**
	 * ServiceProvider element must be one of these.
	 */
	public enum ServiceProvider {
		AGREEMENT_INITIATOR("AgreementInitiator"), 
		AGREEMENT_RESPONDER("AgreementResponder");
		
		String label;
		private ServiceProvider(String label) {
			
			this.label = label;
		}
		
		@Override
		public String toString() {
			return label;
		}
	}


	@XmlElement(name = "AgreementInitiator")
	private String agreementInitiator;
	@XmlElement(name = "AgreementResponder")
	private String agreementResponder;
	@XmlElement(name = "ServiceProvider")
	private String serviceProvider;

	@JsonSerialize(using=DateTimeSerializerJSON.class)
	@JsonDeserialize(using=DateTimeDeserializerJSON.class)
	@XmlElement(name = "ExpirationTime")
	private Date  expirationTime;
	@XmlElement(name = "TemplateId")
	private String templateId;
	@XmlElement(name = "Service", namespace="http://sla.atos.eu")
	private String service;

	public Context() {
	}

	public String getAgreementInitiator() {
		return agreementInitiator;
	}

	public void setAgreementInitiator(String agreementInitiator) {
		this.agreementInitiator = agreementInitiator;
	}

	public String getAgreementResponder() {
		return agreementResponder;
	}

	public void setAgreementResponder(String agreementResponder) {
		this.agreementResponder = agreementResponder;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

}
