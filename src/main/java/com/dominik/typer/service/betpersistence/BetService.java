package com.dominik.typer.service.betpersistence;

import com.dominik.typer.model.Bet;
import com.dominik.typer.model.exceptions.MyAppException;
import com.dominik.typer.service.matchpersistence.MatchService;
import com.dominik.typer.service.userpersistence.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        checkIfUserHasMoneyForBet(userService.getUser(bet.getUserId()).get().getUsername(), bet.getBetAmount());
        BigDecimal negateValue = bet.getBetAmount().negate();
        userService.updateBalance(bet.getUserId(), negateValue);

        betPersistence.saveBet(bet);
    }

    public List<Bet> getBetsFromUser(String username) {
        checkIfUserExistsByUsername(username);
        betPersistence.getBetsFromUser(getIdFromUsername(username));
        return betPersistence.getBetsFromUser(getIdFromUsername(username));
    }
    private void checkIfMatchExists(Integer matchId){
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
    private void checkIfUserHasMoneyForBet(String username, BigDecimal betValue) {
        if (userService.getUserWithUsername(username).get().getBalance().compareTo(betValue) < 0) {
            throw new MyAppException("User does not have enough money");
        }
    }
    private void checkIfUserExistsById(Integer id) {
        if (userService.getUser(id).isEmpty()) {
            throw new MyAppException("User does not exist");
        }
    }
}
