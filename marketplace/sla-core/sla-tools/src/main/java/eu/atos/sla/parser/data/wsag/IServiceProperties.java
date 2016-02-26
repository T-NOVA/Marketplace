package eu.atos.sla.parser.data.wsag;

public interface IServiceProperties {
	public String getName();
	public void setName(String name);
	public String getServiceName();
	public void setServiceName(String serviceName);
	public IVariableSet getVariableSet();
	public void setVariable(IVariableSet variableSet);
}
