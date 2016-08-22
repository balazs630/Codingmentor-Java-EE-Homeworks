package hu.oktatas.java.ee.webshop.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.jboss.logging.Logger;

public class InternalServerError implements ExceptionMapper<ErrorDTO> {

    @Override
    public Response toResponse(ErrorDTO exception) {
        Logger.getLogger(InternalServerError.class.getName()).log(Logger.Level.FATAL, exception);
        return Response.serverError().
                status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(exception.getMessage()).type(MediaType.APPLICATION_JSON).build();
    }

}
