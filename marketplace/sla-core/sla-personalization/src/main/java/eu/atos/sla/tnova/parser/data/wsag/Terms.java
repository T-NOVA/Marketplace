package eu.atos.sla.tnova.parser.data.wsag;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eu.atos.sla.parser.data.wsag.IAllTerms;
import eu.atos.sla.parser.data.wsag.ITerms;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Terms")
public class Terms implements ITerms {

	@XmlElement(name = "All")
	private AllTerms allTerms;

	public IAllTerms getAllTerms() {
		return allTerms;
	}

	public void setAllTerms(IAllTerms allTerms) {
		this.allTerms = (AllTerms)allTerms;
	}

}
