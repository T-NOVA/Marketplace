package eu.atos.sla.parser.data.wsag;

import java.util.List;

public interface IAllTerms {
	public IServiceDescriptionTerm getServiceDescriptionTerm();	
	public void setServiceDescriptionTerm(IServiceDescriptionTerm serviceDescriptionTerm);
	public List<IServiceProperties> getServiceProperties();
	public void setServiceProperties(List<IServiceProperties> serviceProperties);
	public List<IGuaranteeTerm> getGuaranteeTerms();
	public void setGuaranteeTerms(List<IGuaranteeTerm> guaranteeTerms);
}
