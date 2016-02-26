package eu.atos.sla.parser.data.wsag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "KPITarget")
public class KPITarget implements IKPITarget{

	@XmlElement(name = "KPIName", required=true, nillable=false)
	private String kpiName;
	@XmlElement(name = "CustomServiceLevel", required=true, nillable=false)
	private String customServiceLevel;


	public String getKpiName() {
		return kpiName;
	}

	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}

	public String getCustomServiceLevel() {
		return customServiceLevel;
	}

	public void setCustomServiceLevel(String customServiceLevel) {
		this.customServiceLevel = customServiceLevel;
	}
}
