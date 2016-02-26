package eu.atos.sla.datamodel.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;

import eu.atos.sla.datamodel.IServiceProperties;
import eu.atos.sla.datamodel.IVariable;

@Entity
@Table(name = "service_properties")
@NamedQueries({
		@NamedQuery(name = ServiceProperties.QUERY_FIND_ALL, query = "SELECT p FROM ServiceProperties p"),
		@NamedQuery(name = ServiceProperties.QUERY_FIND_BY_NAME, query = "SELECT p FROM ServiceProperties p where p.name = :name") })
public class ServiceProperties implements IServiceProperties, Serializable {

	public final static String QUERY_FIND_ALL = "ServiceProperties.findAll";
	public final static String QUERY_FIND_BY_NAME = "ServiceProperties.findByName";

	private static final long serialVersionUID = 8160422880355293304L;
	private Long id;
	private String name;
	private String serviceName;

	private List<IVariable> variableSet;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ServiceProperties() {
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "name", nullable = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_name")
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@OneToMany(targetEntity = Variable.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "service_properties_id", referencedColumnName = "id", nullable = true)
	public List<IVariable> getVariableSet() {
		return variableSet;
	}

	public void setVariableSet(List<IVariable> variableSet) {
		this.variableSet = variableSet;
	}

}
