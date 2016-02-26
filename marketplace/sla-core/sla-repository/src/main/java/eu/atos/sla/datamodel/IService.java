package eu.atos.sla.datamodel;


public interface IService {

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
}
