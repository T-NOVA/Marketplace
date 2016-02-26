package eu.atos.sla.datamodel.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import eu.atos.sla.datamodel.IService;

/**
 * A POJO object storing a provider's info.
 * 
 * @author rsosa
 */
@Entity
@Table(name = "provider")
@NamedQueries({
		@NamedQuery(name = Service.QUERY_FIND_ALL, query = "SELECT p FROM Service p"),
		@NamedQuery(name = Service.QUERY_FIND_BY_UUID, query = "SELECT p FROM Service p where p.uuid = :uuid"),		
		@NamedQuery(name = Service.QUERY_FIND_BY_NAME, query = "SELECT p FROM Service p where p.name = :name") })
public class Service implements IService, Serializable {
	
	public final static String QUERY_FIND_ALL = "Service.findAll";
	public final static String QUERY_FIND_BY_UUID = "Service.getByUuid";
	public final static String QUERY_FIND_BY_NAME = "Service.getByName";

	private static final long serialVersionUID = -6655604906240872609L;

	Long id; 
	String uuid;
	String name;

	public Service() {
	}
	
	public Service(Long id, String uuid, String name) {
		this.id = id;
		this.uuid = uuid;
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "uuid", unique = true)
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	

}
