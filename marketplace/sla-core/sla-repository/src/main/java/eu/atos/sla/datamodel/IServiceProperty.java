package eu.atos.sla.datamodel;

public interface IServiceProperty {

	/*
	 * Internal generated id
	 */
	Long getId();
	
	/**
	 * ServiceName (from the ServiceProperties element)
	 */
	public String getServiceName();
	
	/**
	 * Name of this ServiceProperty
	 */
	public String getName();
	
	/**
	 * Xsd type of this property
	 */
	public String getMetric();
	
	/**
	 * Reference to a field in the service terms. In our sla, it is a "conceptual" reference.
	 * @return
	 */
	public String getLocation();
	
}
