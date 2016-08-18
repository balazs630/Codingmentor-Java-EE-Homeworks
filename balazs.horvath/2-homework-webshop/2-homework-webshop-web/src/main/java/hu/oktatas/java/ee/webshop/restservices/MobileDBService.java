package hu.oktatas.java.ee.webshop.restservices;

import hu.oktatas.java.ee.webshop.db.MobileDB;
import hu.oktatas.java.ee.webshop.db.exceptions.MobileNotExistException;
import hu.oktatas.java.ee.webshop.util.VerifyLogin;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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

    @EJB
    private transient MobileDB mobileDB;

    @GET
    @Path("/count/{id}")
    public Integer count(@PathParam("id") String id) throws MobileNotExistException {
        return mobileDB.count(mobileDB.getMobileTypeByID(id));
    }

    @PUT
    @Path("/add/{id}")
    public Boolean add(int quantity, @PathParam("id") String id, @Context HttpServletRequest request) {
        VerifyLogin.userLogin(request);
        return mobileDB.returnMobile(mobileDB.getMobileTypeByID(id), quantity);
    }

    @PUT
    @Path("/remove/{id}")
    public Boolean remove(int quantity, @PathParam("id") String id, @Context HttpServletRequest request) {
        VerifyLogin.userLogin(request);
        return mobileDB.reserveMobile(mobileDB.getMobileTypeByID(id), quantity);
    }
}
