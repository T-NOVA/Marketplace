package eu.atos.sla.tnova.parser.data.wsag;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eu.atos.sla.parser.data.wsag.IServiceDescriptionTerm;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ServiceDescriptionTerm")
public class ServiceDescriptionTerm implements IServiceDescriptionTerm{

	@XmlAttribute(name = "Name")
	private String name;
	@XmlAttribute(name = "ServiceName")
	private String serviceName;
	@XmlElement(name = "Requirements")
	private List<Requirement> requirements;
	

	public ServiceDescriptionTerm() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public List<Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}

	
}
