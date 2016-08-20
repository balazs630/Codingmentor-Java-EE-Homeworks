package hu.oktatas.java.ee.webshop.producer;

import hu.oktatas.java.ee.webshop.interceptor.DefaultValidator;
import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorProducer {

    @Produces
    @DefaultValidator
    public Validator produceLogger() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        return vf.getValidator();
    }
}
