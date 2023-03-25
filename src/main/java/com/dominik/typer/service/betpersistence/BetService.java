package com.dominik.typer.service.betpersistence;

import com.dominik.typer.enumerations.MatchOutcome;
import com.dominik.typer.model.Bet;
import com.dominik.typer.model.Match;
import com.dominik.typer.model.exceptions.MyAppException;
import com.dominik.typer.service.matchpersistence.MatchService;
import com.dominik.typer.service.userpersistence.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BetService {
    private final BetPersistence betPersistence;
    private final MatchService matchService;
    private final UserService userService;

    //negate value to update balance
    public void saveBet(Bet bet) {
        checkIfUserExistsById(bet.getUserId());
        checkIfMatchExists(bet.getMatchId());
        Double odds = checkOddsForBet(bet.getMatchId(), bet.getBetType());
        bet.setBetOdds(odds);
        checkIfUserHasMoneyForBet(userService.getUser(bet.getUserId()).get().getUsername(), bet.getBetAmount());
        userService.updateBalance(bet.getUserId(), (-1) * bet.getBetAmount());
        betPersistence.saveBet(bet);
    }

    public List<Bet> getBetsFromUser(String username) {
        checkIfUserExistsByUsername(username);
        betPersistence.getBetsFromUser(getIdFromUsername(username));
        return betPersistence.getBetsFromUser(getIdFromUsername(username));
    }

    public List<Bet> getBetsByMatchId(Integer matchId) {
        checkIfMatchExists(matchId);
        return betPersistence.getBetsFromMatch(matchId);
    }

    private void checkIfMatchExists(Integer matchId) {
        if (matchService.getMatch(matchId).isEmpty()) {
            throw new MyAppException("Match does not exist");
        }
    }

    private void checkIfUserExistsByUsername(String username) {
        if (userService.getUserWithUsername(username).isEmpty()) {
            throw new MyAppException("User does not exist");
        }
    }

    private Integer getIdFromUsername(String username) {
        return userService.getUserWithUsername(username).get().getId();
    }

    private void checkIfUserHasMoneyForBet(String username, Double betValue) {
        if (userService.getUserWithUsername(username).get().getBalance().compareTo(betValue) < 0) {
            throw new MyAppException("User does not have enough money");
        }
    }

    private void checkIfUserExistsById(Integer id) {
        if (userService.getUser(id).isEmpty()) {
            throw new MyAppException("User does not exist");
        }
    }

    private double checkOddsForBet(Integer id, MatchOutcome betType) {
        Match match = matchService.getMatch(id).get();
        Double odds = switch (betType) {
            case HOME_TEAM_WIN -> match.getOddsForHomeTeam();
            case AWAY_TEAM_WIN -> match.getOddsForAwayTeam();
            case DRAW -> match.getOddsForDraw();
        };
        return odds;
    }
}
