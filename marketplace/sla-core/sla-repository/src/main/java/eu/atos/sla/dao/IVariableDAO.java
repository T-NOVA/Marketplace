package eu.atos.sla.dao;

import java.util.List;

import eu.atos.sla.datamodel.IVariable;
import eu.atos.sla.datamodel.bean.Variable;

/**
 * DAO interface to access to the variable information
 * 
 * @author Pedro Rey - Atos
 * 
 */
public interface IVariableDAO  {

	/**
	 * Returns the variable from the database by its Id
	 * 
	 * @param id
	 *            of the variable
	 * @return the corresponding variable from the database
	 */
	public Variable getById(Long id);

	/**
	 * Returns the variable from the database by its name
	 * 
	 * @param id
	 *            of the variable
	 * @return the corresponding Variable from the database
	 */
	public IVariable getByName(String variableName);

	/**
	 * Returns all the variable stored in the database
	 * 
	 * @return all the variable stored in the database
	 */
	public List<IVariable> getAll();

	/**
	 * Stores a variable into the database
	 * 
	 * @param variable to be saved.
	 * @return <code>true</code> if the variable was saved correctly
	 * @throws Exception 
	 */
	public IVariable save(IVariable variable);

	/**
	 * Updates a variable in the database
	 * 
	 * @param variable
	 *            - variable to be updated
	 * @return <code>true</code> if the Variable was saved correctly
	 */
	public boolean update(IVariable variable);

	/**
	 * Deletes a variable from the database
	 * 
	 * @param variable
	 *            to be deleted
	 * @return <code>true</code> if the variable was deleted correctly
	 */
	public boolean delete(IVariable variable);

}
