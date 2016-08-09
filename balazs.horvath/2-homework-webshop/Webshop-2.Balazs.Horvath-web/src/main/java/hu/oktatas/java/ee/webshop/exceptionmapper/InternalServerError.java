package hu.oktatas.java.ee.webshop.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.jboss.logging.Logger;

public class InternalServerError implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        Logger.getLogger(InternalServerError.class.getName()).log(Logger.Level.FATAL, exception);
        return Response.serverError().
                status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
