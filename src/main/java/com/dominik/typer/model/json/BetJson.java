package com.dominik.typer.model.json;

import com.dominik.typer.enumerations.BetType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BetJson {
    Integer id;
    Integer matchId;
    Integer userId;
    Integer pointsFromBetEvent;
    BetType betType;
    Integer betAmount;
}
