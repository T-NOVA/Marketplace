package eu.atos.sla.datamodel;

import java.util.Date;

public interface IEnforcementJob {

	/*
	 * Internally generated id
	 */
	Long getId();
	
	/**
	 * Date of last enforcement start
	 */
	Date getFirstExecuted();
	
	void setFirstExecuted(Date date);
	
	/**
	 * Last datetime where the job was executed
	 */
	Date getLastExecuted();
	
	void setLastExecuted(Date date);
	
	/**
	 * EnforcementJob enabled or not 
	 */
	boolean getEnabled();
	
	void setEnabled(boolean enabled);
	
	/**
	 * Agreement being enforced.
	 */
	IAgreement getAgreement();
	
	void setAgreement(IAgreement agreement);

}
