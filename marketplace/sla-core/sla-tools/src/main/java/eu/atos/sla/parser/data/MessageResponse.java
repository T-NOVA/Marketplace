package eu.atos.sla.parser.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * A POJO Object that stores all the information from a Agreement
 * 
 * @author Pedro Rey - Atos
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "message")
public class MessageResponse {

	@XmlAttribute(name = "code")
	private int code;
	@XmlAttribute(name = "message")
	private String message;
	@XmlAttribute(name = "elementId")
	private String elementId;

	
	public MessageResponse() {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

}
