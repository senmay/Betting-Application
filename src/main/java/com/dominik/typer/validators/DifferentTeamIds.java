package com.dominik.typer.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DifferentTeamIdsValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//@Repeatable(DifferentTeamIds.List.class)
public @interface DifferentTeamIds {
    String message() default "Home team ID and away team ID must be different.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

//    @Target({ TYPE })
//    @Retention(RUNTIME)
//    @Documented
//    @interface List {
//        DifferentTeamIds[] value();
//    }
}
