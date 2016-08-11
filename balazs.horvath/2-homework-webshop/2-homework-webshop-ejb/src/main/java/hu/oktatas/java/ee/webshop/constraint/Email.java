package hu.oktatas.java.ee.webshop.constraint;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Size(min = 7)
@Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z-.]+$")
@ReportAsSingleViolation
@Constraint(validatedBy = {})
@Target(FIELD)
@Retention(RUNTIME)
public @interface Email {

    String message() default "{Email.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
