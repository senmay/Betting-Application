package com.dominik.typer.model.json;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MatchResultJson {
    @Null
    Integer id;
    @NotNull
    Integer matchId;
    @PositiveOrZero
    Integer team1Score;
    @PositiveOrZero
    Integer team2Score;
}
