package hu.oktatas.java.ee.webshop.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import hu.oktatas.java.ee.webshop.beans.UserDTO;
import hu.oktatas.java.ee.webshop.constraint.Name;

public class NameValidator implements ConstraintValidator<Name, UserDTO> {

    private static boolean isFirstAndLastNameAdded(String firstName, String lastName) {
        return firstName != null && lastName != null;
    }

    private static boolean isFirstAndLastNameNull(String firstName, String lastName) {
        return firstName == null && lastName == null;
    }

    @Override
    public void initialize(Name constraintAnnotation) {
        // Empty
    }

    @Override
    public boolean isValid(UserDTO user, ConstraintValidatorContext context) {
        return isFirstAndLastNameAdded(user.getFirstName(), user.getLastName())
                || isFirstAndLastNameNull(user.getFirstName(), user.getLastName());
    }
}
