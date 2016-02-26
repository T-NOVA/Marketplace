package eu.atos.sla.service.rest.helpers.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class DBMissingHelperException extends Exception {

	private static final long serialVersionUID = -5707242844763345340L;

	public DBMissingHelperException(String message){
		super(message);
	}

	public DBMissingHelperException(String s, Throwable t){
		super(s + " - " +serialize(t));
	}

	static private String serialize(Throwable t){
		StringWriter errors = new StringWriter();
		t.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
}
