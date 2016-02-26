package eu.atos.sla.service.types;

import eu.atos.sla.parser.data.wsag.ITemplate;


/**
 * 
 * @author Elena Garrido
 */

public class TemplateParam {
	ITemplate template;
	String originalSerialzedTemplate;
	
	public ITemplate getTemplate() {
		return template;
	}
	public void setTemplate(ITemplate template) {
		this.template= template;
	}
	public String getOriginalSerialzedTemplate() {
		return originalSerialzedTemplate;
	}
	public void setOriginalSerialzedTemplate(String originalSerialzedTemplate) {
		this.originalSerialzedTemplate = originalSerialzedTemplate;
	}
}
