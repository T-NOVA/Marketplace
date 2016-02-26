package eu.atos.sla.parser.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


/*
 * 
 * GuaranteeTermStatus 

 * VIOLATED  -> 0
 * FULFILLED -> 1
 * NON_DETERMINED -> 2	


 <GuaranteeTermStatus name="$gt_name" value="[0,1,2]"/>

 }*/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "guaranteeterm")
@JsonRootName("GuaranteeTermStatus")
public class GuaranteeTermStatus {


	@XmlAttribute(name = "name")
	@JsonProperty("name")
	private String name;
	

	@XmlAttribute(name = "value")
	@JsonProperty("status")
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
