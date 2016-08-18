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
@Consumes(APPLICATION_JSON)
public class UserService implements Serializable {

    @EJB
    private transient UserDB userDB;

    @POST
    public UserDTO add(UserDTO user, @Context HttpServletRequest request) throws UsernameAlreadyTakenException {
            //VerifyLogin.adminLogin(request);
        VerifyLogin.userLogin(request);
        userDB.registrate(user);
        return user;
    }

    @DELETE
    public UserDTO remove(UserDTO user, @Context HttpServletRequest request) {
            //VerifyLogin.adminLogin(request);
        VerifyLogin.userLogin(request);
        userDB.removeUser(user);
        return user;
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
            userDB.getUser(loginUser.getUserName());
            request.getSession(false);
            request.getSession();
            return loginUser;
        }
        throw new UsernameNotExistException("login failed: wrong username or password");
    }
}
