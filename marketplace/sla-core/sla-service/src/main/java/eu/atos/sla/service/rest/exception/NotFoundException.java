package eu.atos.sla.service.rest.exception;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

public class NotFoundException extends Exception implements Serializable {
	private static final long serialVersionUID = 4445376691179333371L;

	public NotFoundException(String s, Throwable t){
		super(s + " - " +serialize(t));
	}

	static private String serialize(Throwable t){
		StringWriter errors = new StringWriter();
		t.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
	
	public NotFoundException(String msg) {
        super(msg);
    }
}