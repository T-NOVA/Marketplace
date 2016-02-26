package eu.atos.sla.datamodel.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import eu.atos.sla.datamodel.IPolicy;

/**
 * A POJO Object that stores all the information from a Policy
 * 
 * @author Pedro Rey - Atos
 */
@Entity
@Table(name = "policy")
@NamedQueries({ @NamedQuery(name = "Policy.findAll", query = "SELECT p FROM Policy p") })
public class Policy implements IPolicy, Serializable {


	private static final long serialVersionUID = 3776951347492029263L;
	
	private Long id;
	private Integer count;
	private Date timeInterval;
	private String variable;

	public Policy() {
	}
	
	public Policy(int count, Date timeInterval) {
		this.count = count;
		this.timeInterval = timeInterval;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "number")
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "time_interval")
	public Date getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(Date timeInterval) {
		this.timeInterval = timeInterval;
	}

	@Column(name = "variable")
	public String getVariable() {
		return variable;
	}
	
	public void setVariable(String variable) {
		this.variable = variable;
	}

}
