package hu.oktatas.java.ee.webshop.beans;

import static hu.oktatas.java.ee.webshop.beans.Color.*;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

public class MobileTypeTest {

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
    public void ApplePhoneWithValidColor() {
        MobileType mobile = new MobileType(ManufacturerType.APPLE, "iPhone 5s", 1, Currency.HKD, BLACK);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobile);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void ApplePhoneWithInvalidColor() {
        MobileType mobile = new MobileType(ManufacturerType.APPLE, "iPhone 6s", 1, Currency.HUF, PURPLE);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobile);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(mobile, violations.iterator().next().getRootBean());
        Assert.assertEquals("{Manufacturer.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void SamsungPhoneWithValidColor() {
        MobileType mobile = new MobileType(ManufacturerType.SAMSUNG, "Galaxy S4", 1, Currency.HUF, WHITE);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobile);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void SamsungPhoneWithInvalidColor() {
        MobileType mobile = new MobileType(ManufacturerType.SAMSUNG, "Galaxy S5", 1, Currency.HUF, GREEN);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobile);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(mobile, violations.iterator().next().getRootBean());
        Assert.assertEquals("{Manufacturer.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void HTCPhoneWithValidColor() {
        MobileType mobile = new MobileType(ManufacturerType.HTC, "One M8", 1, Currency.EUR, RED);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobile);
        Assert.assertEquals(0, violations.size());
    }
}
