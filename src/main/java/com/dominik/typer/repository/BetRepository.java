package com.dominik.typer.repository;

import com.dominik.typer.model.Bet;
import com.dominik.typer.model.entity.BetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<BetEntity, Integer> {
}
