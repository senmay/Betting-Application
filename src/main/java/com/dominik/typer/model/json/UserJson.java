package com.dominik.typer.model.json;


import com.dominik.typer.enumerations.UserRole;
import com.dominik.typer.validators.ValidationGroupBusinessLogic;
import com.dominik.typer.validators.ValidationGroupJson;
import com.dominik.typer.validators.ValidationGroupPutJson;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@Builder
public class UserJson {
    @Null(groups = {ValidationGroupJson.class})
    Integer id;
    @Length(min = 4, max = 20, groups = {ValidationGroupJson.class})
    String username;
    @Min(value = 0, groups = {ValidationGroupBusinessLogic.class})
    @Null(groups = {ValidationGroupJson.class})
    Integer points;
    @Null(groups = {ValidationGroupBusinessLogic.class})
    @Digits(integer = 10, fraction = 2, groups = {ValidationGroupBusinessLogic.class})
    Double balance;
    @NotNull(groups = {ValidationGroupBusinessLogic.class})
    UserRole userType;
    @NotEmpty(groups = {ValidationGroupJson.class, ValidationGroupPutJson.class})
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", groups = {ValidationGroupJson.class})
    String email;
}
