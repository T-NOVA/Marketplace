package eu.atos.sla.parser.json.deserializer;

import eu.atos.sla.parser.UniquePropertyPolymorphicDeserializer;
import eu.atos.sla.parser.data.wsag.IServiceDescriptionTerm;


public class ServiceDescriptionTermDeserializer extends UniquePropertyPolymorphicDeserializer<IServiceDescriptionTerm> {
	private static final long serialVersionUID = 1L;
	
	// Register the abstract class that doesn't provide type information for child implementations
	public ServiceDescriptionTermDeserializer() {
		super(IServiceDescriptionTerm.class);
	}
}



