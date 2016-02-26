package eu.atos.sla.datamodel;

import java.util.Date;

public interface IBreach {

	/*
	 * Internal generated ID
	 */
	Long getId();

	/**
	 * Date and time of the metric that has generated this breach.
	 * 
	 * @return
	 */
	Date getDatetime();

	/**
	 * Name of the kpiName that has generated this breach.
	 */
	String getKpiName();

	String getValue();

	/**
	 * Value of the metric that has generated this breach.
	 * 
	 * @return
	 */

	void setId(Long id);

	void setValue(String value);

	/**
	 * Date and time of the metric that has generated this breach.
	 * 
	 * @return
	 */
	void setDatetime(Date date);

	/**
	 * Name of the metric that has generated this breach.
	 */
	void setKpiName(String metric);

	String getAgreementUuid();

	void setAgreementUuid(String agreementUuid);

}