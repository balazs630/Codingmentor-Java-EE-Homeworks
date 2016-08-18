package hu.oktatas.java.ee.webshop.util;

import hu.oktatas.java.ee.webshop.beans.UserDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ForbiddenException;

public class VerifyLogin {

    public static final String USER_SESSION_ATTRIBUTE = "user";

    private VerifyLogin() {
        // private constructor
    }

    public static void adminLogin(HttpServletRequest request) {
        if (!userLogin(request).isAdmin()) {
            request.getSession().invalidate();
            throw new ForbiddenException("not logged in as admin");
        }
    }

    public static UserDTO userLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userObject = session.getAttribute(USER_SESSION_ATTRIBUTE);
        if ((userObject != null && userObject instanceof UserDTO)) {
            return (UserDTO) userObject;
        } else {
            session.invalidate();
            throw new ForbiddenException("user not logged in");
        }
    }
}
