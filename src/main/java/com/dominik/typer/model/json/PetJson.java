package com.dominik.typer.model.json;

import com.dominik.typer.validators.ValidationGroupBusinessLogic;
import com.dominik.typer.validators.ValidationGroupJson;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PetJson {
    @NotNull
    @Min(value = 3)
    private String id;
    @Length(min = 8, max = 12, groups = {ValidationGroupJson.class})
    private String name;
    @Min(value = 5, groups = {ValidationGroupJson.class, ValidationGroupBusinessLogic.class})
    private Integer age;
}
