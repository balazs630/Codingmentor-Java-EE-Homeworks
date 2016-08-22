package hu.oktatas.java.ee.webshop.exceptionmapper;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Throwable> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(Throwable throwable) {
        logger.log(Level.FATAL, "General Exception", throwable);
        return Response.status(INTERNAL_SERVER_ERROR)
                .entity(new ErrorDTO(throwable.getMessage() 
                        + " " + throwable.getCause()))
                .type(APPLICATION_JSON).build();
    }

}
