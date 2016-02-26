package eu.atos.sla.parser.data.wsag;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eu.atos.sla.parser.data.wsag.custom.CustomBusinessValue;
import eu.atos.sla.parser.data.wsag.custom.ICustomBusinessValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "BusinessValueList")
public class BusinessValueList implements IBusinessValueList { 
	@XmlElement(name = "CustomBusinessValue")
	protected List<CustomBusinessValue> customBusinessValue;

	@XmlElement(name = "Importance")
	protected Integer importance;

	/**
	 * Gets the value of the importance property.
	 */
	public Integer getImportance() {
		return importance;
	}

	/**
	 * Sets the value of the importance property.
	 */
	public void setImportance(Integer value) {
		this.importance = value;
	}
	
	/**
	 * Gets the value of the customBusinessValue property.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ICustomBusinessValue> getCustomBusinessValue() {
		if (customBusinessValue == null) {
			customBusinessValue = new ArrayList<CustomBusinessValue>();
		}
		return (List<ICustomBusinessValue>)(List)this.customBusinessValue;
	}

}
