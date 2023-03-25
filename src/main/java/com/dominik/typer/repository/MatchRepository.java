package com.dominik.typer.repository;

import com.dominik.typer.model.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Integer> {
    @Query("SELECT m FROM matches m WHERE m.dateOfEvent > CURRENT_TIMESTAMP")
    List<MatchEntity> getAllByDateOfEventAfterNow();
}
