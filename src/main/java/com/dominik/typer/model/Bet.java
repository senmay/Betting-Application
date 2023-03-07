package com.dominik.typer.model;

import com.dominik.typer.enumerations.BetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bet {
    Integer id;
    Integer matchId;
    Integer userId;
    boolean pointsFromBetEvent;
    BetType betType;
    Integer betAmount;
}
