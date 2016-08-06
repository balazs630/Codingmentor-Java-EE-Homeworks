package hu.oktatas.java.ee.webshop.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import hu.oktatas.java.ee.webshop.beans.UserDTO;
import hu.oktatas.java.ee.webshop.constraint.DateOfBirth;

public class DateOfBirthValidator implements ConstraintValidator<DateOfBirth, UserDTO> {

    @Override
    public void initialize(DateOfBirth a) {
        // Empty
    }

    @Override
    public boolean isValid(UserDTO user, ConstraintValidatorContext cvc) {
        if (user.getDateOfBirth() != null) {
            return user.getDateOfBirth().before(user.getRegistrationDate());
        }
        return true;
    }
}
