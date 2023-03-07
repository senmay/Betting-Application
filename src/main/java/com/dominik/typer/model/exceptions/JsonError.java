package com.dominik.typer.model.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "jsonerrors")
public class JsonError {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String errorType;
    private String errorMessage;
    private String wrongBody;
    private String timestamp;

}
