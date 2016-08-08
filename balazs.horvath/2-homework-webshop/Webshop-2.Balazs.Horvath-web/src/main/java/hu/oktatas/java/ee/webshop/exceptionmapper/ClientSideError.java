package hu.oktatas.java.ee.webshop.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.jboss.logging.Logger;

public class ClientSideError implements ExceptionMapper<Throwable>{

    @Override
    public Response toResponse(Throwable exception) {
        Logger.getLogger(ClientSideError.class.getName()).log(Logger.Level.ERROR, this);
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    
}
