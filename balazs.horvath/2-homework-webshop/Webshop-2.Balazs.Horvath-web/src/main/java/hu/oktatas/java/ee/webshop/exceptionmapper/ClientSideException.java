package hu.oktatas.java.ee.webshop.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.jboss.logging.Logger;

public class ClientSideException implements ExceptionMapper<Throwable>{

    @Override
    public Response toResponse(Throwable exception) {
        Logger.getLogger(ClientSideException.class.getName()).log(Logger.Level.ERROR, this);
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    
}
