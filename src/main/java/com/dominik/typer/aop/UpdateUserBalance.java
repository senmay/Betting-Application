package com.dominik.typer.aop;

import com.dominik.typer.enumerations.BetType;
import com.dominik.typer.enumerations.MatchOutcome;
import com.dominik.typer.model.Bet;
import com.dominik.typer.model.MatchResult;
import com.dominik.typer.model.json.MatchResultJson;
import com.dominik.typer.model.mapper.BetMapper;
import com.dominik.typer.model.mapper.MatchResultMapper;
import com.dominik.typer.repository.BetRepository;
import com.dominik.typer.service.userpersistence.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class UpdateUserBalance {
    private final BetRepository betRepository;
    private final UserService userService;
    private final BetMapper betMapper;
    private final MatchResultMapper matchResultMapper;
    @AfterReturning(
            pointcut = "execution(* com.dominik.typer.controller.MatchResultController.saveMatchResult(..)) && args(matchResultJson)"
    ,argNames = "matchResultJson")
    public void updateUserBalanceAfterMatch(MatchResultJson matchResultJson) {
        MatchResult matchResult = matchResultMapper.mapFromMatchResultJson(matchResultJson);
        MatchOutcome matchOutcome = matchResult.getMatchOutcome();
        List<Bet> bets = betMapper.mapToListBet(betRepository.findAllByMatchId(matchResult.getMatchId()));

        for (Bet bet : bets) {
            boolean isWinningBet = false;

            if (bet.getBetType() == BetType.HOME_TEAM_WIN && matchOutcome == MatchOutcome.HOME_TEAM_WIN) {
                isWinningBet = true;
            } else if (bet.getBetType() == BetType.AWAY_TEAM_WIN && matchOutcome == MatchOutcome.AWAY_TEAM_WIN) {
                isWinningBet = true;
            } else if (bet.getBetType() == BetType.DRAW && matchOutcome == MatchOutcome.DRAW) {
                isWinningBet = true;
            }
            if (isWinningBet) {
                userService.updateBalance(bet.getUserId(), bet.getBetAmount());
            }
        }
    }
}
