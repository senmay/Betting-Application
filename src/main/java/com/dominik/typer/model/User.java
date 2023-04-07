package com.dominik.typer.model;

import com.dominik.typer.enumerations.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private Integer points;
    private String email;
    private Double balance;
    private UserRole userType;
    private String password;
}
