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
import org.junit.Before;

public class UserDTOTest {

    private static ValidatorFactory vf;
    private static Validator validator;

    private final Calendar regTime = Calendar.getInstance();
    private final Calendar dateOfBirthValid = Calendar.getInstance();
    private final Calendar dateOfBirthInvalid = Calendar.getInstance();

    @BeforeClass
    public static void setUpClass() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @AfterClass
    public static void close() {
        vf.close();
    }

    @Before
    public void setTestDates() {
        dateOfBirthValid.add(Calendar.YEAR, -10); //present time -10 years
        dateOfBirthInvalid.add(Calendar.DATE, +1); //present time +1 day
        regTime.add(Calendar.SECOND, -1); //present time -1 sec (to ensure it's in the past)
    }

    @Test
    public void ValidPassword() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domain.com", regTime);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void InvalidPassword() {
        String invalidPassword = "fakepass";
        UserDTO user = new UserDTO("testuser", invalidPassword, "user@domain.com", regTime);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(invalidPassword, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void ValidName() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domain.com", regTime);
        user.setFirstName("Teszt");
        user.setLastName("Elek");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void InvalidName() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domain.com", regTime);
        user.setFirstName("Teszt");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(user, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void ValidAddress() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domain.com", regTime);
        user.setAddress("1132 Budapest Nyugati tér 1.");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void InvalidAddress() {
        String invalidAddress = "Budapest Nyugati tér 1.";
        UserDTO user = new UserDTO("testuser", "Passs1234", invalidAddress, "user@domain.com", regTime);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(invalidAddress, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void ValidPhone() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domain.com", regTime);
        user.setPhone("+36304208455");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void InvalidPhone() {
        String invalidPhone = "+363042084555";
        UserDTO user = new UserDTO("testuser", "Passs1234", invalidPhone, "user@domain.com", regTime);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(invalidPhone, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void ValidEmail() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domain.com", regTime);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void InvalidEmail() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "userdomain.com", regTime);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(user.getEmail(), violations.iterator().next().getInvalidValue());
    }

    @Test
    public void InvalidEmail2() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domaincom", regTime);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(user.getEmail(), violations.iterator().next().getInvalidValue());
    }

    @Test
    public void ValidDateOfBirth() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domain.com", regTime);
        user.setDateOfBirth(dateOfBirthValid);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void InvalidDateOfBirth() {
        UserDTO user = new UserDTO("testuser", "Passs1234", "user@domain.com", regTime);
        user.setDateOfBirth(dateOfBirthInvalid);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(user, violations.iterator().next().getInvalidValue());
    }
}
