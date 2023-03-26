package com.dominik.typer.model.entity;

import com.dominik.typer.enumerations.MatchOutcome;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="bets")
public class BetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    Integer matchId;
    Integer userId;
    Integer pointsFromBetEvent;
    MatchOutcome betType;
    BigDecimal betAmount;

}
