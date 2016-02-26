package eu.atos.sla.parser.data.wsag;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "VariableSet")
public class VariableSet implements IVariableSet{

	@XmlElement(name = "Variable")
	private List<Variable> variables;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<IVariable> getVariables() {
		return (List<IVariable>)(List)variables;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setVariable(List<IVariable> variables) {
		this.variables = (List<Variable>)(List)variables;
	}
	

}
