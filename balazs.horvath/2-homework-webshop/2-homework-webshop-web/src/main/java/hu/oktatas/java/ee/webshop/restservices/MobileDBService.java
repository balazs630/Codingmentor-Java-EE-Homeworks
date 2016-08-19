package hu.oktatas.java.ee.webshop.restservices;

import hu.oktatas.java.ee.webshop.beans.MobileType;
import hu.oktatas.java.ee.webshop.db.MobileDB;
import hu.oktatas.java.ee.webshop.db.exceptions.MobileNotExistException;
import hu.oktatas.java.ee.webshop.util.VerifyLogin;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/inventory")
@SessionScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MobileDBService implements Serializable {

    @EJB
    private transient MobileDB mobileDB;

    @GET
    @Path("/count/{id}")
    public Integer count(@PathParam("id") String id) {
        return mobileDB.count(id);
    }
    
    @PUT
    @Path("/add/{id}")
    public Boolean add(@PathParam("id") String id, @QueryParam("quantity") Integer quantity,
            @Context HttpServletRequest request) throws MobileNotExistException {
        VerifyLogin.userLogin(request);
        MobileType mobile = mobileDB.getMobileTypeByID(id);
        return mobileDB.reserveMobile(mobile, quantity);
    }

    @PUT
    @Path("/remove/{id}")
    public Boolean remove(@PathParam("id") String id, @QueryParam("quantity") Integer quantity, 
            @Context HttpServletRequest request) throws MobileNotExistException {
        VerifyLogin.userLogin(request);
        return mobileDB.reserveMobile(mobileDB.getMobileTypeByID(id), quantity);
    }
}
