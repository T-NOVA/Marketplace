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
import eu.atos.sla.parser.xml.AgreementParser;


/**
 * 
 * @author Elena Garrido
 */

@Component
@Provider
@Produces(MediaType.APPLICATION_XML)
public class AgreementListXmlMessageBodyWriter implements MessageBodyWriter<List<IAgreement>> {
	private static Logger logger = LoggerFactory.getLogger(AgreementListXmlMessageBodyWriter.class);
	static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
	byte[] serializedData = null;
	Throwable catchedException = null;
	
	@Resource(name="agreementXmlParser")
	IParser<eu.atos.sla.parser.data.wsag.IAgreement> xmlParser;

	IParser<eu.atos.sla.parser.data.wsag.IAgreement> defaultParser = new AgreementParser();
	
	private void initParser() {
		if (xmlParser instanceof NullParser) xmlParser=null;		
	}
	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		String className = List.class.getName()+"<"+IAgreement.class.getName()+">";
		initParser();
		boolean isUsed = false;
		if (genericType!=null)
			isUsed = genericType.toString().equals(className) && mediaType.toString().contains(MediaType.APPLICATION_XML);
		if (isUsed)
			logger.debug("isWriteable:{} --> type:{} genericType:{} mediaType:{} with parser:{}",
					isUsed, type, genericType, mediaType, xmlParser);
		return isUsed;
	}
	
	@Override
	public long getSize(List<IAgreement> agreements, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		StringBuffer tmp = new StringBuffer();
		tmp.append(HEADER);
		tmp.append("<agreements>");
		try {
			for (IAgreement agreement:agreements){
				String agreementData = (xmlParser==null)?
						defaultParser.getSerializedData(agreement.getText()):
						xmlParser.getSerializedData(agreement.getText()); 
				tmp.append(agreementData);
			}
		} catch (ParserException e) {
			catchedException = e;
		}
		tmp.append("</agreements>");
		if (catchedException == null){
			serializedData = tmp.toString().getBytes();
			return  serializedData.length;
		}else
			return 0;
	}

	@Override
	public void writeTo(List<IAgreement> agreement, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> multivaluedMap, OutputStream entityStream)
			throws IOException, WebApplicationException {
		logger.info("writeTo");
		if (catchedException!=null) 
			throw new WebApplicationException(catchedException, Response.Status.INTERNAL_SERVER_ERROR);
		else
			entityStream.write(serializedData);
		
	}
	
}

