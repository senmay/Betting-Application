package com.dominik.typer.model;


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
}
