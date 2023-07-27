package com.dominik.typer.model;

import lombok.*;


@Data
@AllArgsConstructor
@Builder
public class Team {
    private Integer id;
    private String name;
    @Builder.Default
    private Boolean inactive = false;

}

