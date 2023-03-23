package com.dominik.typer.service.betpersistence;

import com.dominik.typer.model.Bet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class BetService {
    private final BetPersistence betPersistence;
    public void saveBet(Bet bet) {
        // buisnessValidation
        betPersistence.saveBet(bet);
    }
}
