package eu.atos.sla.service.messagebodyserializers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

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
import eu.atos.sla.parser.data.wsag.Agreement;
import eu.atos.sla.parser.xml.AgreementParser;


/**
 * 
 * @author Elena Garrido
 */

@Component
@Provider
@Produces(MediaType.APPLICATION_XML)
public class AgreementXmlMessageBodyWriter implements MessageBodyWriter<IAgreement> {
	private static Logger logger = LoggerFactory.getLogger(AgreementXmlMessageBodyWriter.class);
	static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";

	@Resource(name="agreementXmlParser")
	IParser<Agreement> xmlParser;
	Throwable catchedException;
	
	IParser<eu.atos.sla.parser.data.wsag.IAgreement> defaultParser = new AgreementParser();
	
	private void initParser() {
		if (xmlParser instanceof NullParser) xmlParser=null;		
	}

	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		initParser();
		boolean isUsed = (genericType == IAgreement.class) && (mediaType.toString().contains(MediaType.APPLICATION_XML));
		if (isUsed)
			logger.debug("isWritable:{} --> type:{} genericType:{} mediaType:{} with parser:{}",
				isUsed, type, genericType, mediaType, xmlParser);
		return isUsed;
	}
	
	byte[] serializedData = null;
	@Override
	public long getSize(IAgreement agreement, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		if (agreement.getText()!=null){
			String agreementData;
			try {
				agreementData = (xmlParser == null)?
						defaultParser.getSerializedData(agreement.getText()):
							xmlParser.getSerializedData(agreement.getText());
				serializedData =  (new String(HEADER + agreementData)).getBytes();
			} catch (ParserException e) {
				catchedException = e;
			} 
		}else {
			logger.error("Error marshalling data agreement text is null");
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return  serializedData.length;
	}

	@Override
	public void writeTo(IAgreement agreement, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> multivaluedMap, OutputStream entityStream)
			throws IOException, WebApplicationException {
		if (catchedException!=null) 
			throw new WebApplicationException(catchedException, Response.Status.INTERNAL_SERVER_ERROR);
		else
			entityStream.write(serializedData);		
	}
}
