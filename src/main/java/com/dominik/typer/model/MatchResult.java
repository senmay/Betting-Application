package com.dominik.typer.model;

import com.dominik.typer.enumerations.MatchOutcome;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.dominik.typer.enumerations.MatchOutcome.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchResult {
    Integer id;
    Integer matchId;
    Integer team1Score;
    Integer team2Score;

    public MatchOutcome getMatchOutcome() {
        if (team1Score > team2Score) {
            return HOME_TEAM_WIN;
        } else if (team1Score < team2Score) {
            return AWAY_TEAM_WIN;
        } else {
            return DRAW;
        }
    }
}
