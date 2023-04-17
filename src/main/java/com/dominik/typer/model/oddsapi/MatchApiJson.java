package com.dominik.typer.model.oddsapi;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class MatchApiJson {
    String apiId;
    String sportKey;
    LocalDateTime commenceTime;
    Integer homeTeamId;
    Integer awayTeamId;
    Double oddsForHomeTeam;
    Double oddsForDraw;
    Double oddsForAwayTeam;

}
