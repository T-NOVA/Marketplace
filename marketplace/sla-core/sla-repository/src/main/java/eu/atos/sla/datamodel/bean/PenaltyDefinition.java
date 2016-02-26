package eu.atos.sla.datamodel.bean;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

import eu.atos.sla.datamodel.ICompensationDefinition.IPenaltyDefinition;

@Entity
@Table(name="penaltydefinition")
@Access(AccessType.FIELD)
public class PenaltyDefinition extends CompensationDefinition implements IPenaltyDefinition {
	private static final long serialVersionUID = 1L;

	public PenaltyDefinition() {
		super();
	}
	
	public PenaltyDefinition(Date timeInterval,
			String valueUnit, String valueExpression) {

		super(CompensationKind.PENALTY, timeInterval, valueUnit, valueExpression);
	}

	public PenaltyDefinition(int count, String valueUnit, String valueExpression) {	

		super(CompensationKind.PENALTY, count, valueUnit, valueExpression);
	}

	public PenaltyDefinition(int count, Date timeInterval, String action, String valueUnit, String valueExpression, 
			String validity) {
		
		super(CompensationKind.CUSTOM_PENALTY, count, timeInterval, action, valueUnit, valueExpression, validity);
	}
}
