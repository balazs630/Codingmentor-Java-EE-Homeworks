package hu.oktatas.java.ee.webshop.restservices;

import hu.oktatas.java.ee.webshop.db.MobileDB;
import hu.oktatas.java.ee.webshop.db.exceptions.MobileNotExistException;
import hu.oktatas.java.ee.webshop.util.VerifyLogin;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

@Path("/inventory")
@SessionScoped
public class MobileDBService implements Serializable {

    @Inject
    private MobileDB mobileDB;

    public MobileDBService() {
        //Default constructor
    }

    @GET
    @Path("/count/{id}")
    public String count(@PathParam("id") String id) {
        return "\n" + mobileDB.countById(id) + " mobile(s) found with id: " + id;
    }

    @PUT
    @Path("/add/{id}")
    public String add(@PathParam("id") String id, @Context HttpServletRequest request)
            throws MobileNotExistException {
        VerifyLogin.userLogin(request);
        mobileDB.reserveMobile(mobileDB.getMobileTypeByID(id), 1);
        return "Incremented mobile stock amount by 1 for id: " + id;
    }

    @PUT
    @Path("/remove/{id}")
    public String remove(@PathParam("id") String id, @Context HttpServletRequest request)
            throws MobileNotExistException {
        VerifyLogin.userLogin(request);
        mobileDB.reserveMobile(mobileDB.getMobileTypeByID(id), 1);
        return "Decremented mobile stock amount by 1 for id: " + id;
    }
}
