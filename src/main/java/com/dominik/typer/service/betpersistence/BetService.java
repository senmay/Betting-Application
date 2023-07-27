package com.dominik.typer.service.betpersistence;

import com.dominik.typer.enumerations.MatchOutcome;
import com.dominik.typer.model.Bet;
import com.dominik.typer.model.Match;
import com.dominik.typer.model.exceptions.MyAppException;
import com.dominik.typer.service.matchpersistence.MatchPersistence;
import com.dominik.typer.service.matchpersistence.MatchService;
import com.dominik.typer.service.userpersistence.UserPersistence;
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
    private final UserPersistence userPersistence;
    private final MatchPersistence matchPersistence;

    public void saveBet(Bet bet) {
        checkIfUserExistsById(bet.getUserId());
        checkIfMatchExists(bet.getMatchId());
        Double odds = checkOddsForBet(bet.getMatchId(), bet.getBetType());
        bet.setBetOdds(odds);
        checkIfUserHasMoneyForBet(userService.getUser(bet.getUserId()).get().getUsername(), bet.getBetAmount());
        //negate value to update balance
        userPersistence.updateBalance(bet.getUserId(), (-1) * bet.getBetAmount());
        betPersistence.saveBet(bet);
    }

    public List<Bet> getBetsFromUser(String username) {
        checkIfUserExistsByUsername(username);
        betPersistence.getBetsFromUser(getIdFromUsername(username));
        return betPersistence.getBetsFromUser(getIdFromUsername(username));
    }
    private void checkIfMatchExists(Integer matchId) {
        if (matchService.getMatch(matchId).isEmpty()) {
            throw new MyAppException("Match does not exist");
        }
    }

    private void checkIfUserExistsByUsername(String username) {
        if (userPersistence.getUserByUsername(username).isEmpty()) {
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
        if (userPersistence.getUserById(id).isEmpty()) {
            throw new MyAppException("User does not exist");
        }
    }

    private double checkOddsForBet(Integer id, MatchOutcome betType) {
        Match match = matchPersistence.getMatchById(id).get();
        return match.getOddsByMatchOutcome(betType);
    }
}
