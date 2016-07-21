package hu.oktatas.java.ee.webshop.constraint;

import hu.oktatas.java.ee.webshop.validators.DateOfBirthValidator;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DateOfBirthValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface DateOfBirth {

    String message() default "{DateOfBirth.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target(TYPE)
    @Retention(RUNTIME)
    @interface List {

        DateOfBirth[] value();
    }
}
