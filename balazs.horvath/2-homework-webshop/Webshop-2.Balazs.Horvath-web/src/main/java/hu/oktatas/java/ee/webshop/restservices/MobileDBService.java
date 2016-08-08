package hu.oktatas.java.ee.webshop.restservices;

import hu.oktatas.java.ee.webshop.beans.MobileType;
import hu.oktatas.java.ee.webshop.db.MobileDB;
import hu.oktatas.java.ee.webshop.db.exceptions.MobileNotExistException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/inventory")
@SessionScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class MobileDBService implements Serializable {

    @Inject
    private MobileDB mobileDB;

    @GET
    @Path("/count/{id}")
    // @UserLoggedInCheck
    public Integer count(@PathParam("id") MobileType mobile) throws MobileNotExistException {
        return mobileDB.count(mobile);
    }

    @PUT
    @Path("/add/{id}")
    // @AdminLoggedInCheck
    public Boolean add(int quantity, @PathParam("id") MobileType mobile, @Context HttpServletRequest request) {
        return mobileDB.returnMobile(mobile, quantity);
    }

    @PUT
    @Path("/remove/{id}")
    // @AdminLoggedInCheck
    public Boolean remove(Integer quantity, @PathParam("id") MobileType mobile, @Context HttpServletRequest request) {
        return mobileDB.reserveMobile(mobile, quantity);
    }
}
