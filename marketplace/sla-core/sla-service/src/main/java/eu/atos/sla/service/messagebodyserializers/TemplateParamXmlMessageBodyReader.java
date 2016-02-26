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
import eu.atos.sla.parser.xml.TemplateParser;
import eu.atos.sla.service.types.TemplateParam;


/**
 * 
 * @author Elena Garrido
 */

@Component
@Provider
@Consumes(MediaType.APPLICATION_XML)
public class TemplateParamXmlMessageBodyReader implements MessageBodyReader<TemplateParam> {
	private static Logger logger = LoggerFactory.getLogger(TemplateParamXmlMessageBodyReader.class);
	@Resource(name="templateXmlParser")
	IParser<eu.atos.sla.parser.data.wsag.ITemplate> xmlParser;
	
	IParser<eu.atos.sla.parser.data.wsag.ITemplate> defaultParser = new TemplateParser();
	
	private void initParser() {
		if (xmlParser instanceof NullParser) xmlParser=null;		
	}


	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		initParser();
		boolean isUsed =  (type == TemplateParam.class) && mediaType.toString().contains(MediaType.APPLICATION_XML);
		logger.debug("isReadable: {} -->type:{} genericType:{} mediaType:{} with parser:{}",
				isUsed, type, genericType, mediaType, xmlParser);
		return isUsed;
		
	}

	
	@Override
	public TemplateParam readFrom(Class<TemplateParam> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
    	String str = MessageBodyUtils.getStringFromInputStream(entityStream);
		try {
			eu.atos.sla.parser.data.wsag.ITemplate template = (xmlParser==null)?defaultParser.getWsagObject(str):xmlParser.getWsagObject(str);
			TemplateParam templateParam = new TemplateParam();
			templateParam.setTemplate(template);
	    	String templateData = (xmlParser==null)?defaultParser.getWsagAsSerializedData(str):xmlParser.getWsagAsSerializedData(str);
	    	templateParam.setOriginalSerialzedTemplate(removeXMLHeader(templateData));
	    	return templateParam;
		} catch (ParserException e) {
	    	logger.error("Error parsing with "+xmlParser.getClass().getName() );
			throw new WebApplicationException(e, Response.Status.NOT_ACCEPTABLE);
		}
	}

	private String removeXMLHeader(String originalXML){
    	return originalXML.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();		
	}


}
