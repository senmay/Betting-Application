package com.dominik.typer.model;

import com.dominik.typer.enumerations.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private Integer points;
    private String email;
    private BigDecimal balance;
    private UserRole userType;
}
