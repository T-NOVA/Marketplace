package eu.atos.sla.parser;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
public class DateTimeSerializerJSON extends JsonSerializer<Date>{ 

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {
        DateTimeAdapter dateTimeAdapter = new DateTimeAdapter();
        try {
        	jsonGenerator.writeString(dateTimeAdapter.marshal(date));
		} catch (Exception e) {
			throw new JsonGenerationException(e.getMessage(), null);
		}  		
	}

}
