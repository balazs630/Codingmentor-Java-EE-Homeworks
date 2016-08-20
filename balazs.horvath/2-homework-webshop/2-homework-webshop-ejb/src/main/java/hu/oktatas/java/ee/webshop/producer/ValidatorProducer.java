package hu.oktatas.java.ee.webshop.producer;

import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import hu.oktatas.java.ee.webshop.interceptor.ValidatorQualifier;

public class ValidatorProducer {

    @Produces @ValidatorQualifier
    public Validator produceValidator() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        return vf.getValidator();
    }
}
