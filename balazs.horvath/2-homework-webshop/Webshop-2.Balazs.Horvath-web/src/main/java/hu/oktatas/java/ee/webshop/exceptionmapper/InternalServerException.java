package hu.oktatas.java.ee.webshop.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.jboss.logging.Logger;

public class InternalServerException implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        Logger.getLogger(InternalServerException.class.getName()).log(Logger.Level.FATAL, this);
        return Response.serverError().
                status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
