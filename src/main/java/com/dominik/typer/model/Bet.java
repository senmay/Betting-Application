package com.dominik.typer.model;

import com.dominik.typer.enumerations.MatchOutcome;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bet {
    Integer id;
    Integer matchId;
    Integer userId;
    Integer pointsFromBetEvent;
    MatchOutcome betType;
    BigDecimal betAmount;
}
