package eu.atos.sla.service.messagebodyserializers;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import eu.atos.sla.parser.IParser;
import eu.atos.sla.parser.NullParser;
import eu.atos.sla.parser.ParserException;
import eu.atos.sla.service.types.AgreementParam;


/**
 * 
 * @author Elena Garrido
 */

@Component
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class AgreementParamJsonMessageBodyReader implements MessageBodyReader<AgreementParam> {
	private static Logger logger = LoggerFactory.getLogger(AgreementParamJsonMessageBodyReader.class);
	@Resource(name="agreementJsonParser")
	IParser<eu.atos.sla.parser.data.wsag.IAgreement> jsonParser;
	Throwable catchedException;

	private void initParser() {
		if (jsonParser instanceof NullParser) jsonParser=null;		
	}
	
	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		initParser();
		boolean isUsed = (type == AgreementParam.class) && 
				mediaType.toString().contains(MediaType.APPLICATION_JSON) && 
				jsonParser!=null; 
		if (isUsed)
			logger.debug("isReadable:{} --> type:{} genericType:{} mediaType:{} with parser:{}", 
					isUsed, type, genericType, mediaType, jsonParser);
		return isUsed;
	}


	@Override
	public AgreementParam readFrom(Class<AgreementParam> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
			String str = MessageBodyUtils.getStringFromInputStream(entityStream);
			try {
				logger.debug("Received information:"+str);
				eu.atos.sla.parser.data.wsag.IAgreement agreementWSAG = jsonParser.getWsagObject(str);
				String serializedWsagData = jsonParser.getWsagAsSerializedData(str);
				AgreementParam agreementParam = new AgreementParam();
				agreementParam.setAgreement(agreementWSAG);
				agreementParam.setOriginalSerialzedAgreement(removeXMLHeader(serializedWsagData));
				return agreementParam;
			} catch (ParserException e) {
				logger.error("Error parsing"+e.getMessage());
				throw new WebApplicationException(e,Response.Status.NOT_ACCEPTABLE);
			}
	}

	private String removeXMLHeader(String originalXML){
		return originalXML.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();		
	}

 
}
