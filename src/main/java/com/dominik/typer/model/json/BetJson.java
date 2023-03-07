package com.dominik.typer.model.json;

import com.dominik.typer.enumerations.BetType;
import lombok.Value;

@Value
public class BetJson {
    Integer id;
    Integer matchId;
    Integer userId;
    Integer pointsFromBetEvent;
    BetType betType;
    Integer betAmount;
}
