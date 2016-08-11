package hu.oktatas.java.ee.webshop.beans;

import java.util.Calendar;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

public class UserDTOTest {

    private static ValidatorFactory vf;
    private static Validator validator;

    private final static Calendar REGTIME = Calendar.getInstance();
    private final static Calendar DATE_OF_BIRTH_VALID = Calendar.getInstance();
    private final static Calendar DATE_OF_BIRTH_INVALID = Calendar.getInstance();

    @BeforeClass
    public static void setUpClass() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
        DATE_OF_BIRTH_VALID.add(Calendar.YEAR, -10); //present time -10 years
        DATE_OF_BIRTH_INVALID.add(Calendar.DATE, +1); //present time +1 day
        REGTIME.add(Calendar.SECOND, -1); //present time -1 sec (to ensure it's in the past)
    }

    @AfterClass
    public static void close() {
        vf.close();
    }

    @Test
    public void ValidPassword() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domain.com", REGTIME);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void InvalidPassword() {
        String invalidPassword = "fakepass";
        UserDTO user = new UserDTO("testuser", invalidPassword, "user@domain.com", REGTIME);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(invalidPassword, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void ValidName() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domain.com", REGTIME);
        user.setFirstName("Teszt");
        user.setLastName("Elek");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void InvalidName() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domain.com", REGTIME);
        user.setFirstName("Teszt");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(user, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void ValidAddress() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domain.com", REGTIME);
        user.setAddress("1132 Budapest Nyugati tér 1.");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void InvalidAddress() {
        String invalidAddress = "Budapest Nyugati tér 1.";
        UserDTO user = new UserDTO("testuser", "Passs1234", invalidAddress, "user@domain.com", REGTIME);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(invalidAddress, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void ValidPhone() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domain.com", REGTIME);
        user.setPhone("+36304208455");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void InvalidPhone() {
        String invalidPhone = "+363042084555";
        UserDTO user = new UserDTO("testuser", "Passs1234", invalidPhone, "user@domain.com", REGTIME);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(invalidPhone, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void ValidEmail() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domain.com", REGTIME);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void InvalidEmail() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "userdomain.com", REGTIME);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(user.getEmail(), violations.iterator().next().getInvalidValue());
    }

    @Test
    public void InvalidEmail2() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domaincom", REGTIME);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(user.getEmail(), violations.iterator().next().getInvalidValue());
    }

    @Test
    public void ValidDateOfBirth() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domain.com", REGTIME);
        user.setDateOfBirth(DATE_OF_BIRTH_VALID);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void InvalidDateOfBirth() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domain.com", REGTIME);
        user.setDateOfBirth(DATE_OF_BIRTH_INVALID);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(user, violations.iterator().next().getInvalidValue());
    }
}
