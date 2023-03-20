package com.dominik.typer.validators;

import com.dominik.typer.model.json.MatchJson;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class DifferentTeamIdsValidator implements ConstraintValidator<DifferentTeamIds, Object> {

    @Override
    public void initialize(DifferentTeamIds constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object matchJson, ConstraintValidatorContext context) {
        if (matchJson == null) {
            return true;
        }

        if (matchJson instanceof MatchJson m) {
            return m.getHomeTeamId() != null
                    && m.getAwayTeamId() != null
                    && !m.getHomeTeamId().equals(m.getAwayTeamId());
        }

        return false;
    }
}