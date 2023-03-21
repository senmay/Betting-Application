package com.dominik.typer.service.matchpersistence;

import com.dominik.typer.model.Match;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MatchPersistance {
    void save(Match match);
    List<Match> getAllMatches();
    Optional<Match> getMatchById(Integer id);
    void deleteMatchById(Integer id);
    void updateMatchById(Integer id, Match match);
    void saveWithAdmin(String username, Match match);
    List<Match> getAllMatchesPossibleToBet();
    List<Match> getAllMatchesByTeamId(Integer id);
    List<Match> getMatchesByTeamIdWithinTimeRange(Integer id, LocalDateTime startTime, LocalDateTime endTime);
}
