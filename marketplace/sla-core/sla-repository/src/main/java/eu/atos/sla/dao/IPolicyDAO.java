package eu.atos.sla.dao;

import java.util.List;

import eu.atos.sla.datamodel.IPolicy;

/**
 * DAO interface to access to the Policy information
 * 
 * @author Pedro Rey - Atos
 * 
 */
public interface IPolicyDAO {

	/**
	 * Returns the Policy from the database by its Id
	 * 
	 * @param id
	 *            of the Policy
	 * @return the corresponding Policy from the database
	 */
	public IPolicy getById(Long id);

	/**
	 * Returns all the Policy stored in the database
	 * 
	 * @return all the Policy stored in the database
	 */
	public List<IPolicy> getAll();

	/**
	 * Stores a Policy into the database
	 * 
	 * @param Policy
	 *            Policy to be saved.
	 * @return <code>true</code> if the SLAPolicyType was saved correctly
	 * @throws Exception 
	 */
	public IPolicy save(IPolicy policy);

	/**
	 * Updates a Policy in the database
	 * 
	 * @param Policy
	 *            Policy to be updated
	 * @return <code>true</code> if the Policy was saved correctly
	 */
	public boolean update(IPolicy policy);

	/**
	 * Deletes a Policy from the database
	 * 
	 * @param Policy
	 *            to be deleted
	 * @return <code>true</code> if the Policy was deleted correctly
	 */
	public boolean delete(IPolicy policy);

}
