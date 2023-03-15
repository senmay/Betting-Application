package com.dominik.typer.model.json;


import com.dominik.typer.enumerations.UserRole;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class UserJson {
    Integer id;
    String username;
    Integer points;
    BigDecimal balance;
    UserRole userType;
    String email;

}
