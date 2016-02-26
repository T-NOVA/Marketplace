package eu.atos.sla.datamodel;

import java.util.List;

public interface IProvider {

	/*
	 * Internal generated id
	 */
	Long getId();

	/**
	 * The provider is recognized by external parties by this UUID
	 */
	String getUuid();

	/**
	 * Provider's name
	 */
	String getName();

	void setId(Long id);

	/**
	 * The provider is recognized by external parties by this UUID
	 */
	void setUuid(String uuid);

	/**
	 * Provider's name
	 */
	void setName(String name);
	
	
	/**
	 * Template list 
	 */
	public List<ITemplate> getTemplates();
	public void setTemplates(List<ITemplate> templates);
	public void addTemplate(ITemplate template);
}
