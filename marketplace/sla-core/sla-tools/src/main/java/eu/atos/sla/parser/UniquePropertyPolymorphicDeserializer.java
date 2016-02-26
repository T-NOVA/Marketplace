package eu.atos.sla.parser;


import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
 
/**
 * Deserializes documents without a specific field designated for Polymorphic Type
 * identification, when the document contains a field registered to be unique to that type
 *
 * @author robin
 */
public class UniquePropertyPolymorphicDeserializer<T> extends StdDeserializer<T> {
 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8418766062698927042L;
	// the registry of unique field names to Class types
	Class<? extends T> clazz = null;
	
	public UniquePropertyPolymorphicDeserializer(Class<T> clazz) {
		super(clazz);
	}
	
	public void register(Class<? extends T> clazz) {
		this.clazz = clazz;
	}
 
	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@Override
	public T deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectMapper mapper = (ObjectMapper) jp.getCodec();  
		ObjectNode obj = (ObjectNode) mapper.readTree(jp);  
	    return mapper.treeToValue(obj, clazz);
	}
 
}
