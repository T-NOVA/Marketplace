package eu.atos.sla.datamodel;

import java.util.List;

import eu.atos.sla.datamodel.ICompensationDefinition.IPenaltyDefinition;

public interface IBusinessValueList {

	/*
	 * Internally generated id
	 */
	Long getId();

	/**
	 * Relative importance of meeting an objective.
	 * 
	 * This core assumes the higher, the more important, with 0 as minimum value.
	 * @return
	 */
	public int getImportance();
	
	public List<IPenaltyDefinition> getPenalties();
	
	public void addPenalty(IPenaltyDefinition penalty);
}
