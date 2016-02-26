package eu.atos.sla.parser.data.wsag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GuaranteeTerm")
public class GuaranteeTerm implements IGuaranteeTerm{

	@XmlAttribute(name = "Name")
	private String name;
	@XmlElement(name = "ServiceScope")
	private ServiceScope serviceScope;
	@XmlElement(name = "ServiceLevelObjective")
	private ServiceLevelObjective serviceLevelObjective;
	@XmlElement(name = "QualifyingCondition")
	private String qualifyingCondition;
	@XmlElement(name="BusinessValueList")
	private BusinessValueList businessValueList;

	public GuaranteeTerm() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ServiceScope getServiceScope() {
		return serviceScope;
	}

	public void setServiceScope(IServiceScope serviceScope) {
		this.serviceScope = (ServiceScope)serviceScope;
	}

	public IServiceLevelObjective getServiceLevelObjetive() {
		return serviceLevelObjective;
	}

	public void setServiceLevelObjetive(IServiceLevelObjective serviceLevelObjetive) {
		this.serviceLevelObjective = (ServiceLevelObjective)serviceLevelObjetive;
	}

	public String getQualifyingCondition() {
		return qualifyingCondition;
	}

	public void setQualifyingCondition(String qualifyingCondition) {
		this.qualifyingCondition = qualifyingCondition;
	}

	public IBusinessValueList getBusinessValueList() {
		return businessValueList;
	}
	
	public void setBusinessValueList(IBusinessValueList businessValueList) {
		this.businessValueList = (BusinessValueList)businessValueList;
	}
}
