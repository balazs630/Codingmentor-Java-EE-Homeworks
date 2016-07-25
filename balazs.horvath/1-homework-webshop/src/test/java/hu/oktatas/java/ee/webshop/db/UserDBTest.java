package hu.oktatas.java.ee.webshop.db;

import hu.oktatas.java.ee.webshop.beans.UserDTO;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;

public class UserDBTest {

    private static final UserDB USERDB = UserDB.INSTANCE;
    private final Calendar regTime = Calendar.getInstance();
    private UserDTO user;

    @Before
    public void setUpClass() {
        user = new UserDTO("testuser", "Passs1234", "user@domain.com", regTime);
    }

    @Test
    public void registrateTestTrue() {
    }

    @Test
    public void registrateTestFalse() {
    }

    @Test
    public void getUserTest() {
    }

    @Test
    public void authenticateTestTrue() {
    }

    @Test
    public void authenticateTestFalse() {
    }
}
