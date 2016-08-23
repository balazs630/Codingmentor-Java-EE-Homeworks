package hu.oktatas.java.ee.webshop.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Provider
public class UsernameNotExistExceptionMapper implements ExceptionMapper<UsernameNotExistException> {

    @Override
    public Response toResponse(UsernameNotExistException exception) {
        return Response.status(Status.BAD_REQUEST)
                .entity(new ErrorDTO(exception.getMessage()))
                .type(APPLICATION_JSON).build();
    }

}
