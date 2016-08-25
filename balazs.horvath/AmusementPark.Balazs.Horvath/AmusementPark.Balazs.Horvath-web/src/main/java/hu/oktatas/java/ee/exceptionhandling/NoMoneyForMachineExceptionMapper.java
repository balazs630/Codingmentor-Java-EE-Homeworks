package hu.oktatas.java.ee.exceptionhandling;

import hu.oktatas.java.ee.exceptions.MachineNotAffordableException;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import javax.ws.rs.ext.ExceptionMapper;

public class NoMoneyForMachineExceptionMapper implements ExceptionMapper<MachineNotAffordableException> {

    private static final Logger LOG = Logger.getLogger(MachineNotAffordableException.class.getName());
    
    @Override
    public Response toResponse(MachineNotAffordableException exception) {
        LOG.log(SEVERE, null, exception);
        return Response.status(BAD_REQUEST)
                .entity(new ErrorDTO(exception.getMessage()))
                .type(APPLICATION_JSON).build();
    }
}
