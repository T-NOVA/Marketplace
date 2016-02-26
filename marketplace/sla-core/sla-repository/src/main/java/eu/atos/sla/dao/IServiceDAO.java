package eu.atos.sla.dao;

import java.util.List;

import eu.atos.sla.datamodel.IService;

/**
 * DAO interface to access to the Service information
 * 
 * @author Pedro Rey - Atos
 * 
 */
public interface IServiceDAO  {

	/**
	 * Returns the Service from the database by its Id
	 * 
	 * @param id
	 *            of the Service
	 * @return the corresponding Service from the database
	 */
	public IService getById(Long id);

	/**
	 * Returns the Service from the database by its UUID
	 * 
	 * @param id
	 *            of the Service
	 * @return the corresponding Service from the database
	 */
	public IService getByUUID(String uuid);

	/**
	 * Returns the Service from the database by its name
	 * 
	 * @param id
	 *            of the Service
	 * @return the corresponding Service from the database
	 */
	public IService getByName(String serviceName);

	/**
	 * Returns all the Service stored in the database
	 * 
	 * @return all the Service stored in the database
	 */
	public List<IService> getAll();

	/**
	 * Stores a Service into the database
	 * 
	 * @param Service
	 *            - Service to be saved.
	 * @return <code>true</code> if the ServiceType was saved correctly
	 * @throws Exception 
	 */
	public IService save(IService service);

	/**
	 * Updates a Service in the database
	 * 
	 * @param Service
	 *            - Service to be updated
	 * @return <code>true</code> if the Service was saved correctly
	 */
	public boolean update(IService service);

	/**
	 * Deletes a Service from the database
	 * 
	 * @param Service
	 *            to be deleted
	 * @return <code>true</code> if the Service was deleted correctly
	 */
	public boolean delete(IService service);

}
