package eu.atos.sla.parser.data.wsag.custom;

import java.util.Date;
import java.util.List;

public interface ICustomBusinessValue {
	public Integer getCount();
	public Date getDuration();
	public List<Penalty> getPenalties();
	public void addPenalty(Penalty penalty);

}
