package eu.atos.sla.tnova.parser.data.wsag;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eu.atos.sla.parser.data.wsag.GuaranteeTerm;
import eu.atos.sla.parser.data.wsag.IAllTerms;
import eu.atos.sla.parser.data.wsag.IGuaranteeTerm;
import eu.atos.sla.parser.data.wsag.IServiceDescriptionTerm;
import eu.atos.sla.parser.data.wsag.IServiceProperties;
import eu.atos.sla.parser.data.wsag.ServiceProperties;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "All")
public class AllTerms implements IAllTerms {

	@XmlElement(name = "ServiceDescriptionTerm")
	private ServiceDescriptionTerm serviceDescriptionTerm;
	@XmlElement(name = "ServiceProperties")
	private List<ServiceProperties> serviceProperties;
	@XmlElement(name = "GuaranteeTerm")
	private List<GuaranteeTerm> guaranteeTerms;

	public AllTerms() {
	}

	public IServiceDescriptionTerm getServiceDescriptionTerm() {
		return serviceDescriptionTerm;
	}

	public void setServiceDescriptionTerm(IServiceDescriptionTerm serviceDescriptionTerm) {
		this.serviceDescriptionTerm = (ServiceDescriptionTerm)serviceDescriptionTerm;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<IServiceProperties> getServiceProperties() {
		return (List<IServiceProperties>)(List)(List)serviceProperties;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setServiceProperties(List<IServiceProperties> serviceProperties) {
		this.serviceProperties = (List<ServiceProperties>)(List)serviceProperties;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<IGuaranteeTerm> getGuaranteeTerms() {
		return (List<IGuaranteeTerm>)(List)guaranteeTerms;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setGuaranteeTerms(List<IGuaranteeTerm> guaranteeTerms) {
		this.guaranteeTerms = (List<GuaranteeTerm>)(List)guaranteeTerms;
	}
}
