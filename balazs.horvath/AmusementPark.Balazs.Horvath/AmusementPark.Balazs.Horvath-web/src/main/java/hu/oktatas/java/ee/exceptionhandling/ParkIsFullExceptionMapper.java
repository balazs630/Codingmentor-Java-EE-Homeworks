package hu.oktatas.java.ee.exceptionhandling;

import hu.oktatas.java.ee.exceptions.ParkIsFullException;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import javax.ws.rs.ext.ExceptionMapper;

public class ParkIsFullExceptionMapper implements ExceptionMapper<ParkIsFullException> {

    private static final Logger LOG = Logger.getLogger(ParkIsFullExceptionMapper.class.getName());

    @Override
    public Response toResponse(ParkIsFullException exception) {
        LOG.log(SEVERE, null, exception);
        return Response.status(BAD_REQUEST)
                .entity(new ErrorDTO(exception.getMessage()))
                .type(APPLICATION_JSON).build();
    }
}
