package eu.atos.sla.parser.data.wsag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ServiceLevelObjective")
public class ServiceLevelObjective implements IServiceLevelObjective {

	@XmlElement(name = "KPITarget")
	private KPITarget kpitarget;

	public IKPITarget getKpitarget() {
		return kpitarget;
	}

	public void setKpitarget(IKPITarget kpitarget) {
		this.kpitarget = (KPITarget)kpitarget;
	}

}
