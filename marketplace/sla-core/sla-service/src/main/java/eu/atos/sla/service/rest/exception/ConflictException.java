package eu.atos.sla.service.rest.exception;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

public class ConflictException extends Exception implements Serializable {
	private static final long serialVersionUID = 990043384502563348L;

	public ConflictException(String s, Throwable t){
		super(s + " - " +serialize(t));
	}

	static private String serialize(Throwable t){
		StringWriter errors = new StringWriter();
		t.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
	
	public ConflictException(String msg) {
        super(msg);
    }
}