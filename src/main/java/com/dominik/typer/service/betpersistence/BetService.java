package com.dominik.typer.service.betpersistence;

import com.dominik.typer.model.Bet;
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
    public void saveBet(Bet bet) {
        checkIfMatchExists(bet.getMatchId());
        betPersistence.saveBet(bet);
    }

    public List<Bet> getBetsFromUser(String username) {
        checkIfUserExists(username);
        betPersistence.getBetsFromUser(getIdFromUsername(username));
        return betPersistence.getBetsFromUser(getIdFromUsername(username));
    }
    private void checkIfMatchExists(Integer matchId){
        if (matchService.getMatch(matchId).isEmpty()) {
            throw new MyAppException("Match does not exist");
        }
    }
    private void checkIfUserExists(String username) {
        if (userService.getUserWithUsername(username).isEmpty()) {
            throw new MyAppException("User does not exist");
        }
    }
    private Integer getIdFromUsername(String username) {
        return userService.getUserWithUsername(username).get().getId();
    }
}
