package com.dominik.typer.repository;

import com.dominik.typer.model.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Integer> {
    @Query("SELECT m FROM matches m WHERE m.dateOfEvent > CURRENT_TIMESTAMP")
    List<MatchEntity> getAllByDateOfEventAfterNow();
    @Query("SELECT m FROM matches m WHERE m.homeTeamId = :id OR m.awayTeamId = :id")
    List<MatchEntity> getAllMatchesByTeamId(@Param("id") Integer id);
    @Query("SELECT m FROM matches m WHERE (m.homeTeamId = :teamId OR m.awayTeamId = :teamId) AND m.dateOfEvent BETWEEN :startTime AND :endTime")
    List<MatchEntity> findMatchesForTeamWithinTimeRange(@Param("teamId") Integer teamId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);



}
