package eu.atos.sla.parser;

import java.io.Serializable;
/**
 * 
 * @author Elena Garrido
 */
public class ParserException extends Exception implements Serializable {
	private static final long serialVersionUID = 990043384502563348L;

	public ParserException(String msg, Throwable t){
		super(msg,t);
	}

	public ParserException(Throwable t){
		super(t);
	}
	
	public ParserException(String msg) {
        super(msg);
    }
}