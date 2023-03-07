package com.dominik.typer.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    private Integer id;
    private Integer homeTeamId;
    private Integer awayTeamId;
    private Date dateOfEvent;
    private Double oddsForHomeTeam;
    private Double oddsForDraw;
    private Double oddsForAwayTeam;
    private boolean isFinished;
    private Integer matchResultId;
}
