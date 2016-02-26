package eu.atos.sla.datamodel;



public interface IVariable {

	
	/*
	 * Internal generated ID
	 */
	Long getId();
	void setId(Long id);
	
	public String getName();

	public void setName(String name);

	public String getMetric();

	public void setMetric(String metric);

	public String getLocation();

	public void setLocation(String location);

}
