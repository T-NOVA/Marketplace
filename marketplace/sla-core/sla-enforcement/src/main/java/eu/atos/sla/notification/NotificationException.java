package eu.atos.sla.notification;

import java.io.PrintWriter;
import java.io.StringWriter;

public class NotificationException extends Throwable{
	private static final long serialVersionUID = -4138530141028446374L;
	public NotificationException(String s, Throwable t){
		super(s + " - " +serialize(t));
	}

	public NotificationException(String msg) {
        super(msg);
    }

	static private String serialize(Throwable t){
		StringWriter errors = new StringWriter();
		t.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
	
}
