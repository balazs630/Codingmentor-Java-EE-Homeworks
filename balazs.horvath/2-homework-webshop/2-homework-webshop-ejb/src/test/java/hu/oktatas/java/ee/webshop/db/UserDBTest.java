package hu.oktatas.java.ee.webshop.db;

import hu.oktatas.java.ee.webshop.beans.UserDTO;
import hu.oktatas.java.ee.webshop.db.exceptions.UsernameAlreadyTakenException;
import hu.oktatas.java.ee.webshop.db.exceptions.UsernameNotExistException;
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDBTest {

    private UserDB userDB;
    private final Calendar regTime = Calendar.getInstance();
    private UserDTO user, user2;

    @Before
    public void setUpClass() {
        user = new UserDTO("testuser", "Passs1234", "user@domain.com", regTime);
        user2 = new UserDTO("testuser2", "Passs1234", "user@domain.com", regTime);
    }

    @Test
    public void registrateTestTrue() throws UsernameAlreadyTakenException, UsernameNotExistException {
        String username = "newusername";
        user.setUserName(username);
        userDB.registrate(user);
        Assert.assertEquals(userDB.getUser(username), user);
    }

    @Test(expected = UsernameAlreadyTakenException.class)
    public void registrateTestFalse() throws UsernameAlreadyTakenException {
        UserDTO newUser = new UserDTO("testuser", "Passs1234", "user@domain.com", regTime);
        userDB.registrate(user);
        userDB.registrate(newUser);
        Assert.assertEquals(null, newUser);
    }

    @Test
    public void getUserTestTrue() throws UsernameNotExistException, UsernameAlreadyTakenException {
        String username = user.getUserName();
        userDB.registrate(user);
        UserDTO returnedUser = userDB.getUser(username);
        Assert.assertEquals(username, returnedUser.getUserName());
    }

    @Test(expected = UsernameNotExistException.class)
    public void getUserTestFalse() throws UsernameNotExistException {
        String username = "NotExistName";
        UserDTO returnedUser = userDB.getUser(username);
        Assert.assertEquals(null, returnedUser);
    }

    @Test
    public void authenticateTestTrue() throws UsernameAlreadyTakenException {
        String username = user2.getUserName();
        String password = user2.getPassword();
        userDB.registrate(user2);
        boolean isAuthenticationSuccessful = userDB.authenticate(username, password);
        Assert.assertEquals(true, isAuthenticationSuccessful);
    }

    @Test
    public void authenticateTestFalse() throws UsernameAlreadyTakenException {
        String username = "MistypedOne";
        String password = user2.getPassword();
        boolean isAuthenticationSuccessful = userDB.authenticate(username, password);
        Assert.assertEquals(false, isAuthenticationSuccessful);
    }
}
