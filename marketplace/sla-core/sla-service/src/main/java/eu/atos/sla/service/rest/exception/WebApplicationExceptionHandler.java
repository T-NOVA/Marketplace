package eu.atos.sla.service.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.atos.sla.parser.ParserException;
@Provider
public class WebApplicationExceptionHandler implements ExceptionMapper<WebApplicationException>{
	private static Logger logger = LoggerFactory.getLogger(WebApplicationExceptionHandler.class);

	@Override
	public Response toResponse(WebApplicationException exception) {
		if (exception.getCause()!=null){ 	
			if (exception.getCause() instanceof ParserException){
				ParserException pe = (ParserException)exception.getCause();
				return  ExceptionHandlerUtils.buildResponse(Status.NOT_ACCEPTABLE, pe);
			}
			if (exception.getCause().getCause()!=null){
				if (exception.getCause().getCause().getCause()!=null){
					if (exception.getCause().getCause().getCause() instanceof ParserException){
						ParserException pe = (ParserException)exception.getCause().getCause().getCause();
						return  ExceptionHandlerUtils.buildResponse(Status.NOT_ACCEPTABLE, pe);
					}
				}
			}
		}
		logger.info("Not found exception will be thrown");
		return  ExceptionHandlerUtils.buildResponse(Status.NOT_FOUND, exception);
	}

}
