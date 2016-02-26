package eu.atos.sla.service.messagebodyserializers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.parser.IParser;
import eu.atos.sla.parser.NullParser;
import eu.atos.sla.parser.ParserException;


/**
 * 
 * @author Elena Garrido
 */

@Component
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class AgreementListJsonMessageBodyWriter implements MessageBodyWriter<List<IAgreement>> {
	private static Logger logger = LoggerFactory.getLogger(AgreementListJsonMessageBodyWriter.class);
	byte[] serializedData = null;
	Throwable catchedException = null;

	@Resource(name="agreementJsonParser")
	IParser<eu.atos.sla.parser.data.wsag.IAgreement> jsonParser;
	
	private void initParser() {
		if (jsonParser instanceof NullParser) jsonParser=null;		
	}
	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		String className = List.class.getName()+"<"+IAgreement.class.getName()+">";
		boolean isUsed = false;
		initParser();
		if (genericType!=null)
			isUsed = (genericType.toString().equals(className) && 
					mediaType.toString().contains(MediaType.APPLICATION_JSON)) && 
					jsonParser!=null ;
		if (isUsed)
			logger.debug("isWriteable:{} --> type:{} genericType:{} mediaType:{} with parser:{}",
					isUsed, type, genericType, mediaType, jsonParser);
		return isUsed;
	}
	
	@Override
	public long getSize(List<IAgreement> agreements, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		StringBuffer tmp = new StringBuffer();
		if (jsonParser!=null){
			tmp.append("[");
			try {
				for (IAgreement agreement:agreements){
					tmp.append(jsonParser.getSerializedData(agreement.getText()));
					tmp.append(",");
				}
			} catch (ParserException e) {
				catchedException = e;
			}
			if (tmp.length()>1) tmp.deleteCharAt(tmp.length()-1); // we remove the last ',' we've added
			tmp.append("]");
			if (catchedException == null){
				serializedData = tmp.toString().getBytes();
				return  serializedData.length;
			}else
				return 0;
		}else{
			catchedException = new Exception("No Agreement JSON Parser defined");
			return 0;
		}
	}

	@Override
	public void writeTo(List<IAgreement> agreement, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> multivaluedMap, OutputStream entityStream)
			throws IOException, WebApplicationException {
		logger.debug("writeTo");

		if (catchedException!=null) 
			throw new WebApplicationException(catchedException, Response.Status.INTERNAL_SERVER_ERROR);
		else
			entityStream.write(serializedData);
		
	}
	
}

