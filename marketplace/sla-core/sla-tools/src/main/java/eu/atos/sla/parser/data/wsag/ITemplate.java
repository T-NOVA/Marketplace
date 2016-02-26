package eu.atos.sla.parser.data.wsag;

public interface ITemplate {
	public String getTemplateId();
	public void setTemplateId(String templateId);
	public String getName();
	public void setName(String name);
	public IContext getContext();
	public void setContext(IContext context);
	public ITerms getTerms();
	public void setTerms(ITerms terms);
}
