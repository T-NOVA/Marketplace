package eu.atos.sla.parser.data.wsag;

public interface IGuaranteeTerm {
	public String getName();
	public void setName(String name);
	public IServiceScope getServiceScope();
	public void setServiceScope(IServiceScope serviceScope);
	public IServiceLevelObjective getServiceLevelObjetive();
	public void setServiceLevelObjetive(IServiceLevelObjective serviceLevelObjetive);
	public String getQualifyingCondition();
	public void setQualifyingCondition(String qualifyingCondition);
	public IBusinessValueList getBusinessValueList();
	public void setBusinessValueList(IBusinessValueList businessValueList);;

}
