package com.dominik.typer.model.csv;

import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class TeamCSV {
    @Length(min = 3, max = 20)
    String name;

}
