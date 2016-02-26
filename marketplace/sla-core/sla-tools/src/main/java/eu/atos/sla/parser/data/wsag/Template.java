package eu.atos.sla.parser.data.wsag;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * A POJO Object that stores all the information from a Template
 * 
 * @author Pedro Rey - Atos
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Template")
public class Template implements ITemplate{

	@XmlAttribute(name = "TemplateId")
	private String templateId;
	@XmlElement(name = "Name")
	private String name;
	@XmlElement(name = "Context")
	private Context context;
	@XmlElement(name = "Terms")
	private Terms terms;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IContext getContext() {
		return context;
	}

	public void setContext(IContext context) {
		this.context = (Context)context;
	}

	public ITerms getTerms() {
		return terms;
	}

	public void setTerms(ITerms terms) {
		this.terms = (Terms)terms;
	}

	@Override
	public String toString() {

		return "Template[id='" + templateId + "']";
	}	
}