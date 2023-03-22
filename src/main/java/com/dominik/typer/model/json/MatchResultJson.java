package com.dominik.typer.model.json;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MatchResultJson {
    @Null
    Integer id;
    @NotNull
    Integer matchId;
    Integer team1Score;
    Integer team2Score;
}
