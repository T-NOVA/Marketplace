package eu.atos.sla.tnova.parser.xml;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.atos.sla.parser.IParser;
import eu.atos.sla.parser.ParserException;
import eu.atos.sla.parser.ValidationHandler;
import eu.atos.sla.parser.data.wsag.IAgreement;
import eu.atos.sla.tnova.parser.data.wsag.Agreement;
/**
 * 
 * @author Elena Garrido
 */
public class AgreementParser implements IParser<IAgreement> {
	private static Logger logger = LoggerFactory.getLogger(AgreementParser.class);

	/*
	 * getWsagObject receives in serializedData the object information in xml 
	 * must returns a eu.atos.sla.parser.data.wsag.Agreement 
	 */
	@Override
	public IAgreement getWsagObject(String serializedData) throws ParserException{
		IAgreement agreementXML = null;
		try{
			logger.info("Will parse {}", serializedData);
			JAXBContext jaxbContext = JAXBContext.newInstance(Agreement.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setEventHandler(new ValidationHandler());
			agreementXML = (IAgreement)jaxbUnmarshaller.unmarshal(new StringReader(serializedData));
	    	logger.info("Agreement parsed {}", agreementXML);
		}catch(JAXBException e){
			throw new ParserException(e);
		}
    	return agreementXML;
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
