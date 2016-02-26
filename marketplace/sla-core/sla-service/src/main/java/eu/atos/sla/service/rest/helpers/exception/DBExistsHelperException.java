package eu.atos.sla.service.rest.helpers.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class DBExistsHelperException extends Exception {
	private static final long serialVersionUID = 7009728053490946123L;

	public DBExistsHelperException(String message){
		super(message);
	}

	public DBExistsHelperException(String s, Throwable t){
		super(s + " - " +serialize(t));
	}

	static private String serialize(Throwable t){
		StringWriter errors = new StringWriter();
		t.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
	
	
}
