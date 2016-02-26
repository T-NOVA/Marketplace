package eu.atos.sla.service.rest.helpers.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class DBDeletedHelperException extends Exception {
	private static final long serialVersionUID = 8552305438997124332L;

	public DBDeletedHelperException(String message){
		super(message);
	}

	
	public DBDeletedHelperException(String s, Throwable t){
		super(s + " - " +serialize(t));
	}

	static private String serialize(Throwable t){
		StringWriter errors = new StringWriter();
		t.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
	
}
