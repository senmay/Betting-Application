package com.dominik.typer.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Date;
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
    private Date dateOfEvent;
    private Double oddsForHomeTeam;
    private Double oddsForDraw;
    private Double oddsForAwayTeam;
    private boolean isFinished;
    private Integer matchResultId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchEntity that)) return false;
        return isFinished == that.isFinished && Objects.equals(id, that.id) && Objects.equals(homeTeamId, that.homeTeamId) && Objects.equals(awayTeamId, that.awayTeamId) && Objects.equals(dateOfEvent, that.dateOfEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, homeTeamId, awayTeamId, dateOfEvent, isFinished);
    }
}
