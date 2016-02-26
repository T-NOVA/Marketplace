package eu.atos.sla.service.rest.helpers.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class InternalHelperException extends Exception {

	private static final long serialVersionUID = 2138352655902466568L;

	public InternalHelperException(String message){
		super(message);
	}

	public InternalHelperException(String s, Throwable t){
		super(s + " - " +serialize(t));
	}

	static private String serialize(Throwable t){
		StringWriter errors = new StringWriter();
		t.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
}
