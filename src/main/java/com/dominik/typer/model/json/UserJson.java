package com.dominik.typer.model.json;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserJson {
    Integer id;
    Integer points;
    String username;
    String email;

}
