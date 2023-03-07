package com.dominik.typer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchResult {
    Integer matchId;
    Integer team1Score;
    Integer team2Score;

}
