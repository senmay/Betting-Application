package com.dominik.typer.model;


import com.dominik.typer.enumerations.MatchOutcome;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    private Integer id;
    private Integer homeTeamId;
    private Integer awayTeamId;
    private LocalDateTime dateOfEvent;
    private Double oddsForHomeTeam;
    private Double oddsForDraw;
    private Double oddsForAwayTeam;
    private Integer matchResultId;

    public Double getOddsByMatchOutcome(MatchOutcome matchOutcome) {
        return switch (matchOutcome) {
            case HOME_TEAM_WIN -> oddsForHomeTeam;
            case DRAW -> oddsForDraw;
            case AWAY_TEAM_WIN -> oddsForAwayTeam;
        };
    }
}
