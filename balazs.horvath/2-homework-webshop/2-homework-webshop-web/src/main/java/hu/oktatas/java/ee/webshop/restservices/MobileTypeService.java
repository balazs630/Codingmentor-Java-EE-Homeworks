package hu.oktatas.java.ee.webshop.restservices;

import hu.oktatas.java.ee.webshop.db.exceptions.MobileNotExistException;
import hu.oktatas.java.ee.webshop.beans.MobileType;
import hu.oktatas.java.ee.webshop.db.MobileDB;
import hu.oktatas.java.ee.webshop.util.VerifyLogin;
import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ejb.EJB;

@Path("/mobiletypes")
@SessionScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class MobileTypeService implements Serializable {

    @EJB
    private transient MobileDB mobileDB;

    @POST
    @Path("/add")
    public MobileType add(MobileType type, @Context HttpServletRequest request) {
            //VerifyLogin.adminLogin(request);
        VerifyLogin.userLogin(request);
        return mobileDB.addNewMobileType(type);
    }

    @DELETE
    public MobileType remove(MobileType type, @Context HttpServletRequest request) throws MobileNotExistException {
            //VerifyLogin.adminLogin(request);
        VerifyLogin.userLogin(request);    
        if (mobileDB.remove(type)) {
            return type;
        }
        throw new MobileNotExistException("remove failed: mobile not exist");
    }

    @GET
    public Collection<MobileType> getMobileTypes() {
        return (Collection<MobileType>) mobileDB.getReservedMobileDB();
    }
}
