package eu.atos.sla.parser.json.deserializer;

import eu.atos.sla.parser.UniquePropertyPolymorphicDeserializer;
import eu.atos.sla.parser.data.wsag.IServiceScope;


public class ServiceScopeDeserializer extends UniquePropertyPolymorphicDeserializer<IServiceScope> {
	private static final long serialVersionUID = 1L;
	
	// Register the abstract class that doesn't provide type information for child implementations
	public ServiceScopeDeserializer() {
		super(IServiceScope.class);
	}
}



