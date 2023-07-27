package com.dominik.typer.model.json;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyCsvFile {
    Integer homeTeamId;
    Integer awayTeamId;
    LocalDateTime dateOfEvent;
    Double oddsForHomeTeam;
    Double oddsForDraw;
    Double oddsForAwayTeam;

}
