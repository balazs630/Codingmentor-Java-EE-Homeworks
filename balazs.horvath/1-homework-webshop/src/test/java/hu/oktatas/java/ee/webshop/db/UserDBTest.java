package hu.oktatas.java.ee.webshop.db;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserDBTest {

    private static ValidatorFactory vf;
    private static Validator validator;

    @BeforeClass
    public static void setUpClass() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @AfterClass
    public static void close() {
        vf.close();
    }

    @Test
    public void registrateTest() {
    }

    @Test
    public void getUserTest() {
    }

    @Test
    public void authenticateTest() {
    }
}
