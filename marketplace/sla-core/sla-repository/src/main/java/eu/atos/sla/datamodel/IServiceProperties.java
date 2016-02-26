package eu.atos.sla.datamodel;

import java.util.List;

public interface IServiceProperties {

	public Long getId();

	public void setId(Long id);

	public String getName();

	public void setName(String name);

	public String getServiceName();

	public void setServiceName(String serviceName);

	public List<IVariable> getVariableSet();

	public void setVariableSet(List<IVariable> variableSet);

}
