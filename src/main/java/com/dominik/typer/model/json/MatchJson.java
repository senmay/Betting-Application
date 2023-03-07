package com.dominik.typer.model.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Date;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class MatchJson {
    Integer id;
    Integer homeTeamId;
    Integer awayTeamId;
    Date dateOfEvent;
    Double oddsForHomeTeam;
    Double oddsForDraw;
    Double oddsForAwayTeam;
    boolean isFinished;
    Integer matchResultId;
}
