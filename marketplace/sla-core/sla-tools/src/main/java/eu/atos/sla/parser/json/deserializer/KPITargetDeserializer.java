package eu.atos.sla.parser.json.deserializer;

import eu.atos.sla.parser.UniquePropertyPolymorphicDeserializer;
import eu.atos.sla.parser.data.wsag.IKPITarget;


public class KPITargetDeserializer extends UniquePropertyPolymorphicDeserializer<IKPITarget> {
	private static final long serialVersionUID = 1L;
	
	// Register the abstract class that doesn't provide type information for child implementations
	public KPITargetDeserializer() {
		super(IKPITarget.class);
	}
}



