package eu.atos.sla.parser;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
public class DateTimeDeserializerJSON extends JsonDeserializer<Date>{ 


	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        DateTimeAdapter dateTimeAdapter = new DateTimeAdapter();
        try {
			return dateTimeAdapter.unmarshal(node.asText());
		} catch (Exception e) {
			throw new JsonParseException(e.getMessage(), null);
		}  
	}

}
