package com.dominik.typer.model.entity;

import com.dominik.typer.enumerations.BetType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

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
    BetType betType;
    Integer betAmount;

}
