package eu.atos.sla.parser.xml;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.atos.sla.parser.IParser;
import eu.atos.sla.parser.ParserException;
import eu.atos.sla.parser.ValidationHandler;
import eu.atos.sla.parser.data.wsag.ITemplate;
import eu.atos.sla.parser.data.wsag.Template;
public class TemplateParser implements IParser<ITemplate> {
	private static Logger logger = LoggerFactory.getLogger(TemplateParser.class);


	/*
	 * getWsagObject receives in serializedData the object information in xml 
	 * must returns a eu.atos.sla.parser.data.wsag.Template
	 */
	@Override
	public ITemplate getWsagObject(String serializedData) throws ParserException{
		ITemplate templateXML = null;
		try{
			logger.info("Will parse {}", serializedData);
			JAXBContext jaxbContext = JAXBContext.newInstance(Template.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setEventHandler(new ValidationHandler());			
			templateXML = (ITemplate)jaxbUnmarshaller.unmarshal(new StringReader(serializedData));
			logger.info("Template parsed {}", templateXML);
		}catch(JAXBException e){
			throw new ParserException(e);
		}
		return templateXML;

	}

	/*
	 * getWsagAsSerializedData receives in serializedData xml  
	 * must return information following and xml in wsag standard.
	 */
	@Override
	public String getWsagAsSerializedData(String serializedData) throws ParserException {
		return serializedData;
	}

	/*
	 * getSerializedData receives in wsagSerialized the information in wsag standard as it was generated with the
	 * getWsagAsSerializedData method and returns it in xml
	 */
	@Override
	public String getSerializedData(String wsagSerialized) throws ParserException{
		return wsagSerialized;
	}
	
	
}
