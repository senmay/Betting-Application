package com.dominik.typer.model.json;

import com.dominik.typer.validators.DifferentTeamIds;
import com.dominik.typer.validators.ValidationGroupJson;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@DifferentTeamIds(groups = {ValidationGroupJson.class})
public class MatchJson {
    @Null(groups = {ValidationGroupJson.class})
    Integer id;
    @NotNull(groups = {ValidationGroupJson.class})
    Integer homeTeamId;
    @NotNull(groups = {ValidationGroupJson.class})
    Integer awayTeamId;
    @NotNull(groups = {ValidationGroupJson.class})
    LocalDateTime dateOfEvent;
    Double oddsForHomeTeam;
    Double oddsForDraw;
    Double oddsForAwayTeam;
    @NotNull(groups = {ValidationGroupJson.class})
    Boolean isFinished;
    Integer matchResultId;
}
