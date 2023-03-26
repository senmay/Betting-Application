package com.dominik.typer.model.json;


import com.dominik.typer.validators.ValidationGroupJson;
import jakarta.validation.constraints.Null;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class TeamJson {
    @Null(groups = {ValidationGroupJson.class})
    Integer id;
    @Length(min = 3, max = 20, groups = {ValidationGroupJson.class})
    String name;
    @Null(groups = {ValidationGroupJson.class})
    Boolean inactive;
}
