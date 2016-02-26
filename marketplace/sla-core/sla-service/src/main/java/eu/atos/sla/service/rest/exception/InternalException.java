package eu.atos.sla.service.rest.exception;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

public class InternalException extends Exception implements Serializable {
	private static final long serialVersionUID = 3056566523068112767L;

	public InternalException(String s, Throwable t){
		super(s + " - " +serialize(t));
	}

	static private String serialize(Throwable t){
		StringWriter errors = new StringWriter();
		t.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
	
	public InternalException(String msg) {
        super(msg);
    }
}