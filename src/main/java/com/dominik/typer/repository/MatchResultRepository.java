package com.dominik.typer.repository;

import com.dominik.typer.model.entity.MatchResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchResultRepository extends JpaRepository<MatchResultEntity, Integer> {
    MatchResultEntity getMatchResultEntityById(Integer id);
    MatchResultEntity getMatchResultEntityByMatchId(Integer matchId);
    Boolean existsByMatchId(Integer matchId);
}
