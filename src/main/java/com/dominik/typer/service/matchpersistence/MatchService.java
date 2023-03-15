package com.dominik.typer.service.matchpersistence;

import com.dominik.typer.model.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchPersistance matchPersistance;
    public void saveMatch(Match match) {
        matchPersistance.save(match);
    }
    public void saveMatchWithAdmin(String username, Match match) {
        matchPersistance.saveWithAdmin(username, match);
    }
    public void deleteMatch(Integer id) {
        matchPersistance.deleteMatchById(id);
    }
    public Optional<Match> getMatch(Integer id) {
        return matchPersistance.getMatchById(id);
    }
    public List<Match> getMatches(){
        return matchPersistance.getAllMatches();
    }
    public void updateMatch(Integer id, Match match) {
        matchPersistance.updateMatchById(id, match);
    }

    public List<Match> getMatchesPossibleToBet() {
        return matchPersistance.getAllMatchesPossibleToBet();
    }
}
