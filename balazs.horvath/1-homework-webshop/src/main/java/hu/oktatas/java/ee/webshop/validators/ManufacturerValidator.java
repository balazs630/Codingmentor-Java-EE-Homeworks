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
    public boolean isValid(MobileType value, ConstraintValidatorContext cvc) {
        if (value.getManufacturer().equals(ManufacturerType.APPLE)) {
            return value.getColor().equals(Color.BLACK) || value.getColor().equals(Color.WHITE);
        }
        if (value.getManufacturer().equals(ManufacturerType.SAMSUNG)) {
            return !value.getColor().equals(Color.GREEN);
        }
        return true;
    }
}
