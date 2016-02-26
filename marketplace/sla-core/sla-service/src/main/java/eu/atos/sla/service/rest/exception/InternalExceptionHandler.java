package eu.atos.sla.service.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
@Provider
public class InternalExceptionHandler implements ExceptionMapper<InternalException>{

	@Override
	public Response toResponse(InternalException exception) {
		return  ExceptionHandlerUtils.buildResponse(Status.INTERNAL_SERVER_ERROR, exception);
	}

}
