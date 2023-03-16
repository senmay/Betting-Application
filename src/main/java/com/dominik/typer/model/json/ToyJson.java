package com.dominik.typer.model.json;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ToyJson {
    @NotEmpty
    private String name;
    @NotEmpty
    private String color;
}
