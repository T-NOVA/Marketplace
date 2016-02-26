package eu.atos.sla.service.rest.helpers.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ParserHelperException extends Exception {
	private static final long serialVersionUID = -9036820268608651580L;

	public ParserHelperException(String message){
		super(message);
	}

	public ParserHelperException(String s, Throwable t){
		super(s + " - " +serialize(t));
	}

	static private String serialize(Throwable t){
		StringWriter errors = new StringWriter();
		t.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
}
