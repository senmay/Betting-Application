package com.dominik.typer.validators;

import com.dominik.typer.model.exceptions.MyAppException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
@Component
@AllArgsConstructor
public class GeneralValidator
{
    private final Validator validator;
    public <T> void validateObject(T object, Class<?>... groups)
    {
        Set<ConstraintViolation<T>> violations = validator.validate(object, groups);
        if (!violations.isEmpty())
        {
            throw new MyAppException(violations.toString());
        }
    }
    public void validatePathVariable(Integer id){
        if (id == null || id <= 0) {
            throw new MyAppException("Id cannot be null or less than 1");
        }
    }
}
