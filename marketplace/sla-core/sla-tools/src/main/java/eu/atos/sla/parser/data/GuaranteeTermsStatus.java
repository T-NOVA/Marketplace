package eu.atos.sla.parser.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/*
 * 
 *  * GuaranteeTermStatus Status

 * VIOLATED  -> 0
 * FULFILLED -> 1
 * NON_DETERMINED -> 2	
 * 
 * 
 * GET /agreements/{agreementId}/status
 Accept: application/xml

 <GuaranteeTermsStatus AgreementId="$agreementId" value="">
 <GuaranteeTermsStatus>
 <GuaranteeTermStatus name="$gt_name" value="[0,1,2]"/>
 <GuaranteeTermStatus name="$gt_name" value="[0,1,2]"/>
 </GuaranteeTermsStatus>

 }*/


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "guaranteestatus")
@JsonPropertyOrder({ "AgreementId", "guaranteestatus", "guaranteeterms" })
@JsonRootName(value = "GuaranteeStatus")
public class GuaranteeTermsStatus {


	@XmlAttribute(name = "AgreementId")
	@JsonProperty("AgreementId")
	private String agreementId;

	@XmlAttribute(name = "value")
	@JsonProperty("guaranteestatus")
	private String value;

	@XmlElement(name = "guaranteetermstatus")
	@JsonProperty("guaranteeterms")
	private List<GuaranteeTermStatus> guaranteeTermsStatus;

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<GuaranteeTermStatus> getGuaranteeTermsStatus() {
		return guaranteeTermsStatus;
	}

	public void setGuaranteeTermsStatus(
			List<GuaranteeTermStatus> guaranteeTermsStatus) {
		this.guaranteeTermsStatus = guaranteeTermsStatus;
	}

}
