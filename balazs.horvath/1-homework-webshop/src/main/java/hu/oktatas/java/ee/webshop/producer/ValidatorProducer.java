package hu.oktatas.java.ee.webshop.producer;

import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import hu.oktatas.java.ee.webshop.annotations.ValidatorQualifier;

public class ValidatorProducer {

    @Produces
    @ValidatorQualifier
    public Validator produceLogger() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        return vf.getValidator();
    }
}
