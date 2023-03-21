package com.dominik.typer.repository;

import com.dominik.typer.model.entity.BetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<BetEntity, Integer> {
    List<BetEntity> findAllByUserId(Integer id);
}
