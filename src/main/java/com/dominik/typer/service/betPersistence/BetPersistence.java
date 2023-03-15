package com.dominik.typer.service.betPersistence;

import com.dominik.typer.model.Bet;

import java.util.List;

public interface BetPersistence {
    void saveBet(Bet bet);
    List<Bet> getAllBets();
    void deleteBetById(Integer id);
}
