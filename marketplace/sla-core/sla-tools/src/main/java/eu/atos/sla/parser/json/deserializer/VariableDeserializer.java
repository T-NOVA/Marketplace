package eu.atos.sla.parser.json.deserializer;

import eu.atos.sla.parser.UniquePropertyPolymorphicDeserializer;
import eu.atos.sla.parser.data.wsag.IVariable;



public class VariableDeserializer extends UniquePropertyPolymorphicDeserializer<IVariable> {
	private static final long serialVersionUID = 1L;
	
	// Register the abstract class that doesn't provide type information for child implementations
	public VariableDeserializer() {
		super(IVariable.class);
	}
}



