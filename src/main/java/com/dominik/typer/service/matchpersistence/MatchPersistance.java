package com.dominik.typer.service.matchpersistence;

import com.dominik.typer.model.Match;

import java.util.List;

public interface MatchPersistance {
    void save(Match match);
    List<Match> getAllMatches();
    Match getMatchById(Integer id);
    void deleteMatchById(Integer id);
    void updateMatchById(Integer id, Match match);
    void saveWithAdmin(String username, Match match);
    List<Match> getAllMatchesPossibleToBet();
}
