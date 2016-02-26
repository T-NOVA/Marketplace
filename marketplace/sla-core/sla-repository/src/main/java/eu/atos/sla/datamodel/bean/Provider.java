package eu.atos.sla.datamodel.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

import eu.atos.sla.datamodel.IProvider;
import eu.atos.sla.datamodel.ITemplate;

/**
 * A POJO object storing a provider's info.
 * 
 * @author rsosa, prey
 */
@Entity
@Table(name = "provider")
@NamedQueries({
		@NamedQuery(name = Provider.QUERY_FIND_ALL, query = "SELECT p FROM Provider p"),
		@NamedQuery(name = Provider.QUERY_FIND_BY_UUID, query = "SELECT p FROM Provider p where p.uuid = :uuid"),		
		@NamedQuery(name = Provider.QUERY_FIND_BY_NAME, query = "SELECT p FROM Provider p where p.name = :name") })
public class Provider implements IProvider, Serializable {
	
	public final static String QUERY_FIND_ALL = "Provider.findAll";
	public final static String QUERY_FIND_BY_UUID = "Provider.getByUuid";
	public final static String QUERY_FIND_BY_NAME = "Provider.getByName";

	private static final long serialVersionUID = -6655604906240872609L;

	private Long id; 
	private String uuid;
	private String name;
	private List<ITemplate> templates;

	public Provider() {
	}
	
	public Provider(Long id, String uuid, String name) {
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
	
	@Column(name = "uuid")
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	@Column(name = "name", unique=true,nullable=false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@OneToMany(targetEntity = Template.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = true)
	public List<ITemplate> getTemplates() {
		return templates;
	}

	public void setTemplates(List<ITemplate> templates) {
		this.templates = templates;
	}

	public void addTemplate(ITemplate template){
		if (templates == null) templates = new ArrayList<ITemplate>();
		templates.add(template);
	}
}
