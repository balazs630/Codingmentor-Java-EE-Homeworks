package hu.oktatas.java.ee.webshop.restservices;

import hu.oktatas.java.ee.webshop.beans.UserDTO;
import hu.oktatas.java.ee.webshop.db.UserDB;
import hu.oktatas.java.ee.webshop.db.exceptions.UsernameAlreadyTakenException;
import hu.oktatas.java.ee.webshop.db.exceptions.UsernameNotExistException;
import hu.oktatas.java.ee.webshop.util.VerifyLogin;
import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ejb.EJB;

@Path("/users")
@SessionScoped
@Produces(APPLICATION_JSON)

public class UserService implements Serializable {

    @EJB
    private transient UserDB userDB;

    @POST
    @Consumes(APPLICATION_JSON)
    public UserDTO add(UserDTO user, @Context HttpServletRequest request) throws UsernameAlreadyTakenException {
        VerifyLogin.adminLogin(request);
        return userDB.registrate(user);
    }

    @DELETE
    @Consumes(APPLICATION_JSON)
    public boolean remove(UserDTO user, @Context HttpServletRequest request) {
        VerifyLogin.adminLogin(request);
        return userDB.removeUser(user);
    }

    @GET
    @Path("/id/{id}")
    public UserDTO getUserById(@PathParam("id") String id) throws UsernameNotExistException {
        return userDB.getUser(id);
    }

    @GET
    public Collection<UserDTO> getUsers() {
        return (Collection<UserDTO>) userDB.getUserDataBase();
    }

    @POST
    @Path("/login")
    public UserDTO login(UserDTO loginUser, @Context HttpServletRequest request) throws UsernameNotExistException {
        if (userDB.authenticate(loginUser.getUserName(), loginUser.getPassword())) {
            UserDTO user = userDB.getUser(loginUser.getUserName());
            HttpSession session = request.getSession(false);
            if (session == null) {
                request.getSession();
                return user;

            } else {
                session.invalidate();
            }
        }
        throw new UsernameNotExistException("login failed: wrong username or password");
    }

}
