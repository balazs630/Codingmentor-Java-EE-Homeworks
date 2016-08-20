package hu.oktatas.java.ee.webshop.restservices;

import hu.oktatas.java.ee.webshop.beans.MobileType;
import hu.oktatas.java.ee.webshop.db.MobileDB;
import hu.oktatas.java.ee.webshop.db.exceptions.MobileNotExistException;
import hu.oktatas.java.ee.webshop.util.VerifyLogin;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/inventory")
@SessionScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MobileDBService implements Serializable {

    @Inject
    private MobileDB mobileDB;

    public MobileDBService() {
    }

    @GET
    @Path("/count/{id}")
    public Integer count(@PathParam("id") String id) {
        return mobileDB.countById(id);
    }

    @PUT
    @Path("/add/{id}")
    public Boolean add(@PathParam("id") String id, @Context HttpServletRequest request)
            throws MobileNotExistException {
        VerifyLogin.userLogin(request);
        MobileType mobile = mobileDB.getMobileTypeByID(id);
        return mobileDB.reserveMobile(mobile, 1);
    }

    @PUT
    @Path("/remove/{id}")
    public Boolean remove(@PathParam("id") String id, @Context HttpServletRequest request)
            throws MobileNotExistException {
        VerifyLogin.userLogin(request);
        return mobileDB.reserveMobile(mobileDB.getMobileTypeByID(id), 1);
    }
}
