package eu.atos.sla.parser.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eu.atos.sla.datamodel.ICompensation.IPenalty;
import eu.atos.sla.datamodel.IViolation;
import eu.atos.sla.datamodel.ICompensationDefinition.IPenaltyDefinition;
import eu.atos.sla.parser.DateTimeDeserializerJSON;
import eu.atos.sla.parser.DateTimeSerializerJSON;

/**
 * A POJO Object that stores all the information from a Penalty
 * 
 * @author rsosa
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "penalty")
public class Penalty  {

	@XmlElement(name = "uuid")
	private String uuid;
	@XmlElement(name = "agreement_id")
	private String agreementId;
	@JsonSerialize(using=DateTimeSerializerJSON.class)
	@JsonDeserialize(using=DateTimeDeserializerJSON.class)
	@XmlElement(name = "datetime")
	private Date datetime;
	@XmlElement(name = "definition")
	private eu.atos.sla.parser.data.wsag.custom.Penalty definition;
	@XmlElement(name = "violation")
	private eu.atos.sla.parser.data.PenaltyViolation violation;

	public Penalty() {
	}
	
	public Penalty(IPenalty penalty) {
		this.uuid = penalty.getUuid();
		this.agreementId = penalty.getAgreementId();
		this.datetime = penalty.getDatetime();
		
		IPenaltyDefinition def = penalty.getDefinition();
		this.definition = new eu.atos.sla.parser.data.wsag.custom.Penalty(
				def.getAction(), def.getValueExpression(), def.getValueUnit(), def.getValidity());


		IViolation vio = penalty.getViolation();

		//this.violation= new eu.atos.sla.parser.data.Violation(vio.getId(), vio.getUuid(), vio.getServiceName(), vio.getServiceScope(), vio.getDatetime(), vio.getExpectedValue(), vio.getActualValue(), vio.getKpiName(), vio.getContractUuid());
		this.violation= new eu.atos.sla.parser.data.PenaltyViolation(vio.getExpectedValue(), vio.getActualValue(), vio.getKpiName());
	}

	public String getUuid() {
		return uuid;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public Date getDatetime() {
		return datetime;
	}

	public eu.atos.sla.parser.data.wsag.custom.Penalty getDefinition() {
		return definition;
	}

	public eu.atos.sla.parser.data.PenaltyViolation getViolation() {
		return violation;
	}
}
