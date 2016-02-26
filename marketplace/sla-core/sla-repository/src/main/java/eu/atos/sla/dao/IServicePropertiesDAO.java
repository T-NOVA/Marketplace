package eu.atos.sla.dao;

import java.util.List;

import eu.atos.sla.datamodel.IServiceProperties;
import eu.atos.sla.datamodel.bean.ServiceProperties;

/**
 * DAO interface to access to the ServiceProperties information
 * 
 * @author Pedro Rey - Atos
 * 
 */
public interface IServicePropertiesDAO  {

	/**
	 * Returns the ServiceProperties from the database by its Id
	 * 
	 * @param id
	 *            of the ServiceProperties
	 * @return the corresponding ServiceProperties from the database
	 */
	public ServiceProperties getById(Long id);

	/**
	 * Returns the ServiceProperties from the database by its name
	 * 
	 * @param id
	 *            of the ServiceProperties
	 * @return the corresponding Service from the database
	 */
	public IServiceProperties getByName(String serviceName);

	/**
	 * Returns all the ServiceProperties stored in the database
	 * 
	 * @return all the ServiceProperties stored in the database
	 */
	public List<IServiceProperties> getAll();

	/**
	 * Stores a ServiceProperties into the database
	 * 
	 * @param ServiceProperties to be saved.
	 * @return <code>true</code> if the ServiceProperties was saved correctly
	 * @throws Exception 
	 */
	public IServiceProperties save(IServiceProperties serviceProperties);

	/**
	 * Updates a ServiceProperties in the database
	 * 
	 * @param ServiceProperties
	 *            - ServiceProperties to be updated
	 * @return <code>true</code> if the Service was saved correctly
	 */
	public boolean update(IServiceProperties serviceProperties);

	/**
	 * Deletes a ServiceProperties from the database
	 * 
	 * @param ServiceProperties
	 *            to be deleted
	 * @return <code>true</code> if the ServiceProperties was deleted correctly
	 */
	public boolean delete(IServiceProperties serviceProperties);

}
