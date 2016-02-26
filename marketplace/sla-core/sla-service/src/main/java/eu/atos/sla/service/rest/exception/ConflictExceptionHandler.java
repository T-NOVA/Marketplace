package eu.atos.sla.service.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
@Provider
public class ConflictExceptionHandler implements ExceptionMapper<ConflictException>{

	@Override
	public Response toResponse(ConflictException exception) {
		return  ExceptionHandlerUtils.buildResponse(Status.CONFLICT, exception);
	}

}
