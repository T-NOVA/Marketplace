package eu.atos.sla.parser.json.deserializer;

import eu.atos.sla.parser.UniquePropertyPolymorphicDeserializer;
import eu.atos.sla.parser.data.wsag.IServiceLevelObjective;


public class ServiceLevelObjectiveDeserializer extends UniquePropertyPolymorphicDeserializer<IServiceLevelObjective> {
	private static final long serialVersionUID = 1L;
	
	// Register the abstract class that doesn't provide type information for child implementations
	public ServiceLevelObjectiveDeserializer() {
		super(IServiceLevelObjective.class);
	}
}



