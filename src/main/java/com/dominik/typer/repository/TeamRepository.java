package com.dominik.typer.repository;

import com.dominik.typer.model.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {
    Optional<TeamEntity> getByName(String name);
    void deleteByName(String name);
    boolean existsByName(String name);
    Optional<TeamEntity> findByName(String name);
}
