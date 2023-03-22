package com.dominik.typer.model.json;

import com.dominik.typer.enumerations.BetType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class BetJson {
    @Null
    Integer id;
    @NotNull
    Integer matchId;
    @NotNull
    Integer userId;
    @Null
    Integer pointsFromBetEvent;
    @NotNull
    BetType betType;
    @Positive
    BigDecimal betAmount;
}
