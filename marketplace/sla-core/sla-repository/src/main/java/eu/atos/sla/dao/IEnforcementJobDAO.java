package eu.atos.sla.dao;

import java.util.Date;
import java.util.List;

import eu.atos.sla.datamodel.IEnforcementJob;

public interface IEnforcementJobDAO {
	/**
	 * Retrieves enabled jobs not executed since <code>since</code>
	 */
	List<IEnforcementJob> getNotExecuted(Date since);

	/**
	 * Returns the GuaranteeTerm from the database by its Id
	 * 
	 * @param id
	 *            of the GuaranteeTerm
	 * @return the corresponding GuaranteeTerm from the database
	 */
	IEnforcementJob getById(Long id);

	/**
	 * Retrieves the job associated with <code>agreementId</code>.
	 * 
	 * @return EnforcementJob if exists; else <code>null</code>
	 */
	IEnforcementJob getByAgreementId(String agreementId);

	/**
	 * Returns all the EnforcementJob stored in the database
	 * 
	 * @return all the EnforcementJob stored in the database
	 */
	public List<IEnforcementJob> getAll();

	/**
	 * Stores an EnforcementJob into the database
	 * 
	 * @param EnforcementJob
	 *            EnforcementJob to be saved.
	 * @return <code>true</code> if the EnforcementJob was saved correctly
	 * @throws Exception 
	 */
	public IEnforcementJob save(IEnforcementJob expected);

	/**
	 * Deletes an EnforcementJob from the database
	 * 
	 * @param EnforcementJob
	 *            to be deleted
	 * @return <code>true</code> if the EnforcementJob was deleted correctly
	 */
	public boolean delete(IEnforcementJob enforcementJob);



}
