package eu.atos.sla.datamodel;

import java.util.Date;

public interface IPolicy {

	/*
	 * Internal generated ID
	 */
	Long getId();

	void setId(Long id);

	/**
	 * The variable name this policy applies to.
	 */
	String getVariable();

	void setVariable(String variable);

	/**
	 * Defines how many breaches are needed to raise a violation. Defaults to 1.
	 */
	Integer getCount();

	void setCount(Integer count);

	/**
	 * If specified, defines that "count" breaches in this time interval are
	 * needed to raise a violation.
	 */
	Date getTimeInterval();

	void setTimeInterval(Date date);
}