package hu.oktatas.java.ee.webshop.interceptor;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import hu.oktatas.java.ee.webshop.annotations.Validate;
import hu.oktatas.java.ee.webshop.annotations.ValidatorQualifier;
import javax.validation.ValidationException;

@Interceptor
@BeanValidation
public class ValidatorInterceptor {

    @Inject
    @ValidatorQualifier
    private Validator validator;

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws ValidationException {
        try {
            validateParameters(ic.getParameters());
            return ic.proceed();
        } catch (Exception ex) {
            throw new ValidationException("Validation error:" + ex);
        }
    }

    private void validateParameters(Object[] parameters) {
        Arrays.asList(parameters).stream().filter(p -> p.getClass().isAnnotationPresent(Validate.class)).forEach(p -> validateBean(p));
    }

    private void validateBean(Object o) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        Optional<String> errorMessage = violations.stream().map(e -> "Validation error: " + e.getMessage() + " - property: " + e.getPropertyPath().toString() + " . ").reduce(String::concat);
        if (errorMessage.isPresent()) {
            throw new ValidationException(errorMessage.get());
        }
    }

}
