package eu.atos.sla.service.rest.helpers.exception;

public class HelperException extends Exception {
	public enum Code{ DB_DELETED, PARSER, DB_EXIST, DB_MISSING, INTERNAL};
	Code code;
	String message;
	private static final long serialVersionUID = -6211381925574253221L;

	public HelperException(Code code, String message){
		this.code = code;
		this.message = message;
	}

	public Code getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	
}
