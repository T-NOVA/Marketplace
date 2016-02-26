package eu.atos.sla.datamodel;

import java.util.Date;

import eu.atos.sla.datamodel.ICompensationDefinition.IPenaltyDefinition;

public interface ICompensation {

	public interface IPenalty extends ICompensation {
		
		IPenaltyDefinition getDefinition();

		/**
		 * Last violation that generated this penalty.
		 */
		IViolation getViolation();
	}
	
	public interface IReward extends ICompensation {
		
	}

	/*
	 * Internal generated ID
	 */
	Long getId();

	/**
	 * Internal generated UUID. The interested external parties are going to
	 * identify this violation by the UUID.
	 */
	String getUuid();

	/**
	 * AgreementId where this compensation has been enforced.
	 */
	String getAgreementId();

	/**
	 * Date and time when the compensation was raised.
	 */
	Date getDatetime();

	/**
	 * Name of the kpi that has generated this compensation.
	 */
	String getKpiName();
}
