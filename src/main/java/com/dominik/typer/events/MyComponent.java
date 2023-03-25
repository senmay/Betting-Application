package com.dominik.typer.events;

import com.dominik.typer.enumerations.MatchOutcome;
import com.dominik.typer.model.Bet;
import com.dominik.typer.service.betpersistence.BetPersistence;
import com.dominik.typer.service.matchresultpersistence.MatchResultService;
import com.dominik.typer.service.userpersistence.UserPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dominik.typer.enumerations.MatchOutcome.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class MyComponent {

    private final UserPersistence userPersistence;
    private final MatchResultService matchResultService;
    private final BetPersistence betPersistence;

    @Async
    public Double recalculateWinnings(Double betAmount, Double betOdds) {
        log.error("Recalculating");
        return betAmount * betOdds;
    }
    @EventListener
    @Async
    public void handleMatchResultEvent(MatchResultEvent event) {
        log.error("Custom event received");
        MatchOutcome matchOutcome = matchResultService.getMatchOutcome(event.getMatchResult());
        List<Bet> bets = betPersistence.getBetsFromMatch(event.getMatchResult().getMatchId());

        for (Bet bet : bets) {
            if (checkIfBetIsWinning(matchOutcome, bet)) {
                Double ammountToAdd = recalculateWinnings(bet.getBetAmount(),bet.getBetOdds());
                userPersistence.updateBalance(bet.getUserId(), ammountToAdd);
            }
        }
    }
    private boolean checkIfBetIsWinning(MatchOutcome matchOutcome, Bet bet) {
        if (bet.getBetType() == HOME_TEAM_WIN && matchOutcome == HOME_TEAM_WIN) {
            return true;
        } else if (bet.getBetType() == AWAY_TEAM_WIN && matchOutcome == AWAY_TEAM_WIN) {
            return true;
        } else return bet.getBetType() == DRAW && matchOutcome == DRAW;
    }
}
