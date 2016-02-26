package eu.atos.sla.parser.data.wsag;

import java.util.List;

import eu.atos.sla.parser.data.wsag.custom.ICustomBusinessValue;

public interface IBusinessValueList {
	public Integer getImportance();
	public void setImportance(Integer value);
	public List<ICustomBusinessValue> getCustomBusinessValue();
}
