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
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

@Path("/users")
@SessionScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService implements Serializable {

    @EJB
    private UserDB userDB;

    @POST
    public UserDTO add(UserDTO user, @Context HttpServletRequest request) 
            throws UsernameAlreadyTakenException {
        VerifyLogin.adminLogin(request);
        userDB.registrate(user);
        return user;
    }

    @DELETE
    @Path("/{username}")
    public String remove(@PathParam("username") String username, @Context HttpServletRequest request) 
            throws UsernameNotExistException {
        VerifyLogin.adminLogin(request);
        if (userDB.removeUser(username)) {
            return username;
        } else {
            throw new UsernameNotExistException("no such user to remove");
        }
    }

    @GET
    @Path("/{id}")
    public UserDTO getUserById(@PathParam("id") String id) throws UsernameNotExistException {
        return userDB.getUser(id);
    }

    @GET
    public Collection<UserDTO> getUsers() {
        return userDB.getUserDataBase().values();
    }

    @POST
    @Path("/login")
    public UserDTO login(UserDTO loginUser, @Context HttpServletRequest request) 
            throws UsernameNotExistException {
        if (userDB.authenticate(loginUser.getUserName(), loginUser.getPassword())) {
            UserDTO loggedUser = userDB.getUser(loginUser.getUserName());
            HttpSession session = request.getSession(false);
            session.setAttribute("user", loggedUser);
            return loginUser;
        }
        throw new UsernameNotExistException("login failed: wrong username or password");
    }
}
