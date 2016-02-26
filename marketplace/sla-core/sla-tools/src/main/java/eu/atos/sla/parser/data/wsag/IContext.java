package eu.atos.sla.parser.data.wsag;

import java.util.Date;

public interface IContext {
	
	
	public String getAgreementInitiator();
	public void setAgreementInitiator(String agreementInitiator);
	public String getAgreementResponder();
	public void setAgreementResponder(String agreementResponder);
	public String getServiceProvider();
	public void setServiceProvider(String serviceProvider);
	public Date getExpirationTime();
	public void setExpirationTime(Date expirationTime);
	public String getTemplateId();	
	public void setTemplateId(String templateId);
	public String getService();
	public void setService(String service);
}
