package eu.atos.sla.parser.data.wsag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ServiceProperties")
public class ServiceProperties implements IServiceProperties{

	@XmlAttribute(name = "Name")
	private String name;
	@XmlAttribute(name = "ServiceName")
	private String serviceName;
	@XmlElement(name = "VariableSet")
	private VariableSet variableSet;

	public ServiceProperties() {
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

	public IVariableSet getVariableSet() {
		return variableSet;
	}

	public void setVariable(IVariableSet variableSet) {
		this.variableSet = (VariableSet)variableSet;
	}

}
