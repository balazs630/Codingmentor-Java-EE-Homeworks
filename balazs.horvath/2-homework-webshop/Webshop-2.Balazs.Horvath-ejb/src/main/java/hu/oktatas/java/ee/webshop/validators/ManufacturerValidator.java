package hu.oktatas.java.ee.webshop.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import hu.oktatas.java.ee.webshop.beans.MobileType;
import hu.oktatas.java.ee.webshop.beans.ManufacturerType;
import hu.oktatas.java.ee.webshop.beans.Color;
import hu.oktatas.java.ee.webshop.constraint.Manufacturer;

public class ManufacturerValidator implements ConstraintValidator<Manufacturer, MobileType> {

    @Override
    public void initialize(Manufacturer constraintAnnotation) {
        // Empty
    }

    @Override
    public boolean isValid(MobileType mobile, ConstraintValidatorContext cvc) {
        if (mobile.getManufacturer().equals(ManufacturerType.APPLE)) {
            return mobile.getColor().equals(Color.BLACK) || mobile.getColor().equals(Color.WHITE);
        }
        if (mobile.getManufacturer().equals(ManufacturerType.SAMSUNG)) {
            return !mobile.getColor().equals(Color.GREEN);
        }
        return true;
    }
}
