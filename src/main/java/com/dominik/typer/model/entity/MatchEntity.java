package com.dominik.typer.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="matches")
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer homeTeamId;
    private Integer awayTeamId;
    private LocalDateTime dateOfEvent;
    private Double oddsForHomeTeam;
    private Double oddsForDraw;
    private Double oddsForAwayTeam;
    private Integer matchResultId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchEntity that = (MatchEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(homeTeamId, that.homeTeamId) && Objects.equals(awayTeamId, that.awayTeamId) && Objects.equals(dateOfEvent, that.dateOfEvent) && Objects.equals(oddsForHomeTeam, that.oddsForHomeTeam) && Objects.equals(oddsForDraw, that.oddsForDraw) && Objects.equals(oddsForAwayTeam, that.oddsForAwayTeam) && Objects.equals(matchResultId, that.matchResultId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, homeTeamId, awayTeamId, dateOfEvent, oddsForHomeTeam, oddsForDraw, oddsForAwayTeam, matchResultId);
    }
}
