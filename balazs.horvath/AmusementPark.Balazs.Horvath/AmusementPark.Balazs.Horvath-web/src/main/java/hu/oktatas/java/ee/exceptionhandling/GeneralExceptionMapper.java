package hu.oktatas.java.ee.exceptionhandling;

import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import javax.ws.rs.ext.ExceptionMapper;

public class GeneralExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger LOG = Logger.getLogger(GeneralExceptionMapper.class.getName());
    
    @Override
    public Response toResponse(Throwable exception) {
        LOG.log(SEVERE, null, exception);
        return Response.status(INTERNAL_SERVER_ERROR)
                .entity(new ErrorDTO(exception.getMessage()))
                .type(APPLICATION_JSON).build();
    }  
}
