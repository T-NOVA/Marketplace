package eu.atos.sla.evaluation.guarantee;

import java.util.Date;
import java.util.List;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IBreach;

/**
 * Defines the access to a repository of breaches. This repository is needed for the PoliciedServiceLevelEvaluator.
 * 
 * @see PoliciedServiceLevelEvaluator
 * 
 * @author rsosa
 *
 */
public interface IBreachRepository {
	
	/**
	 * Get the agreement breaches in a specified interval (inclusive ends)
	 * 
	 * @param agreement to check.
	 * @param kpiName that generated the breach
	 * @param begin inclusive begin date.
	 * @param end  inclusive end date.
	 */
	List<IBreach> getBreachesByTimeRange(IAgreement agreement, String kpiName, Date begin, Date end);
	
	/**
	 * Persist in the repository a list of breaches.
	 */
	void saveBreaches(List<IBreach> breaches);
}