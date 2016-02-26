package eu.atos.sla.parser;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ValidationHandler implements ValidationEventHandler {
	private static Logger logger = LoggerFactory.getLogger(ValidationHandler.class);

	@Override
	public boolean handleEvent(ValidationEvent validationEvent) {
		if (validationEvent.getLinkedException()==null){
			logger.warn("detected " +validationEvent.getMessage() +"  it will be ignored");			
			return true;
		}
		return false;
	}

}
