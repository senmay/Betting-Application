package com.dominik.typer.model.json;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PetJson {
    @NotNull
    private String id;
    @Length(min = 8, max = 12, groups = {ValidationGroupOne.class})
    private String name;
    @Min(value = 5, groups = {ValidationGroupOne.class, ValidationGroupTwo.class})
    private Integer age;

    public interface ValidationGroupOne {};
    public interface ValidationGroupTwo {};
}
