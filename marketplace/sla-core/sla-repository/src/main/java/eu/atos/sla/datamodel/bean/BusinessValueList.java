package eu.atos.sla.datamodel.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;

import eu.atos.sla.datamodel.IBusinessValueList;
import eu.atos.sla.datamodel.ICompensationDefinition.IPenaltyDefinition;

@Entity
@Table(name = "business_value_list")
@Access(AccessType.FIELD)
public class BusinessValueList implements IBusinessValueList, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name="importance", nullable=false)
	private int importance;
	
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@OneToMany(targetEntity = PenaltyDefinition.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "business_value_id", referencedColumnName = "id", nullable = true)
	private List<IPenaltyDefinition> penalties;

	public BusinessValueList() {
		this.importance = 0;
		this.penalties = new ArrayList<IPenaltyDefinition>();
	}
	
	public BusinessValueList(int importance, Collection<IPenaltyDefinition> penalties) {
	
		this.importance = importance;
		this.penalties = new ArrayList<IPenaltyDefinition>();
		if (penalties != null) {
			this.penalties.addAll(penalties);
		}
	}
	
	@Override
	public List<IPenaltyDefinition> getPenalties() {
		/*
		 * provides more collection encapsulation. Each item may still be mutable. 
		 */
		return Collections.unmodifiableList(penalties);
	}

	@Override
	public Long getId() {
		
		return id;
	}

	@Override
	public int getImportance() {
		
		return importance;
	}
	
	@Override
	public void addPenalty(IPenaltyDefinition penalty) {
		
		this.penalties.add(penalty);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getPenalties() == null) ? 0 : getPenalties().hashCode());
		result = prime * result + importance;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BusinessValueList)) {
			return false;
		}
		return equals((BusinessValueList) obj);
	}
	

	public boolean equals(BusinessValueList other) {
		
		/*
		 * list compare is broken if using hibernate.
		 */
		ArrayList<IPenaltyDefinition> aux = new ArrayList<IPenaltyDefinition>(getPenalties());
		
		boolean result = importance == other.getImportance() &&
				aux.equals(other.getPenalties());
		return result;
	}
}