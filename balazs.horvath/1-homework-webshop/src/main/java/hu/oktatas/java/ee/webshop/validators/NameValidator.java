package hu.oktatas.java.ee.webshop.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import hu.oktatas.java.ee.webshop.beans.UserDTO;
import hu.oktatas.java.ee.webshop.constraint.Name;

public class NameValidator implements ConstraintValidator<Name, UserDTO> {

    public boolean isFirstAndLastNameAdded(String firstName, String lastName){
        if (firstName != null && lastName == null) {
            return false;
        }
        if (lastName != null && firstName == null)
            return false;
        else
            return true;
    }
    
    @Override
    public void initialize(Name constraintAnnotation) {
        // Empty
    }

    @Override
    public boolean isValid(UserDTO user, ConstraintValidatorContext context) {
         return isFirstAndLastNameAdded(user.getFirstName(), user.getLastName());
    }
}
