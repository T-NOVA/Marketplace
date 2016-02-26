package eu.atos.sla.parser.data.wsag;

public interface IAgreement {
	public String getAgreementId();
	public void setAgreementId(String agreementId);
	public String getName();
	public void setName(String name);
	public IContext getContext();
	public void setContext(IContext context);
	public ITerms getTerms();
	public void setTerms(ITerms terms);
}
