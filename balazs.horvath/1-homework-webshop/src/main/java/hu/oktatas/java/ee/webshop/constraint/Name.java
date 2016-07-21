package hu.oktatas.java.ee.webshop.constraint;

import hu.oktatas.java.ee.webshop.validators.NameValidator;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = NameValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface Name {

    String message() default "{Name.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target(TYPE)
    @Retention(RUNTIME)
    @interface List {

        Name[] value();
    }
}
