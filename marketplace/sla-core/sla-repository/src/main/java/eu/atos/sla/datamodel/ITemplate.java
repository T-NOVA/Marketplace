package eu.atos.sla.datamodel;



public interface ITemplate {

	/*
	 * Internal generated ID
	 */
	Long getId();
	void setId(Long id);
	
	/**
	 * This template is recognized by external parties by this internally generated UUID. 
	 */
	
	String getUuid();
	void setUuid(String uuid);
	
	/**
	 * Template body. This is an ws-agreement-compliant xml.
	 * NOTE: String? Maybe there is a better type.
	 */
	String getText();
	void setText(String text);
	

	/** 
	 * Service from the context
	 */
	public String getServiceId();
	public void setServiceId(String serviceId);
	
	/** 
	 * Name from the template
	 */
	public String getName();
	public void setName(String name);
	
	
	/** 
	 * Provider from the template
	 */
	public IProvider getProvider();
	public void setProvider(IProvider provider); 
	
}
