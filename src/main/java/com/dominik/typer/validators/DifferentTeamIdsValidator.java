package com.dominik.typer.validators;

import com.dominik.typer.model.json.MatchJson;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class DifferentTeamIdsValidator implements ConstraintValidator<DifferentTeamIds, MatchJson> {

    @Override
    public void initialize(DifferentTeamIds constraintAnnotation) {
    }

    @Override
    public boolean isValid(MatchJson matchJson, ConstraintValidatorContext context) {
        if (matchJson == null) {
            return true;
        }

        return matchJson.getHomeTeamId() != null
                && matchJson.getAwayTeamId() != null
                && !matchJson.getHomeTeamId().equals(matchJson.getAwayTeamId());
    }
}