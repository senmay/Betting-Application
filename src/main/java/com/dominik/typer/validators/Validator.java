package com.dominik.typer.validators;

import com.dominik.typer.model.User;
import jakarta.validation.ValidatorFactory;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import java.util.Set;
import java.util.logging.Logger;

public class Validator {
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final jakarta.validation.Validator validator = factory.getValidator();
    public static boolean validateUser(User user){
        Logger logger = Logger.getLogger(User.class.getName());
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        for (ConstraintViolation<User> violation : violations){
            logger.warning(violation.getMessage());
        }
        if (!violations.isEmpty()){
            return false;
        }
        return true;
    }

}
