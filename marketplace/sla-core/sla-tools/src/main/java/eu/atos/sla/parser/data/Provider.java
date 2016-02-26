package eu.atos.sla.parser.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A POJO object storing a provider's info.
 * 
 * @author Pedro Rey
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "provider")
public class Provider{

	@XmlElement(name = "id")
	Long id;
	@XmlElement(name = "uuid")
	String uuid;
	@XmlElement(name = "name")
	String name;

	public Provider() {
	}

	public Provider(Long id, String uuid, String name) {
		this.id = id;
		this.uuid = uuid;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
