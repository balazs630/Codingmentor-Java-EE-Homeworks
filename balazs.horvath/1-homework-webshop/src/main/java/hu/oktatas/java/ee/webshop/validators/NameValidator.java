package hu.oktatas.java.ee.webshop.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import hu.oktatas.java.ee.webshop.beans.UserDTO;
import hu.oktatas.java.ee.webshop.constraint.Name;

public class NameValidator implements ConstraintValidator<Name, UserDTO> {

    @Override
    public void initialize(Name constraintAnnotation) {
        // Empty
    }

    @Override
    public boolean isValid(UserDTO value, ConstraintValidatorContext context) {
        if (value.getFirstName() != null && value.getLastName() == null) {
            return false;
        }
        return !(value.getLastName() != null && value.getFirstName() == null);
    }
}
