package com.dominik.typer.service.matchpersistence;

import com.dominik.typer.model.Match;
import com.dominik.typer.model.exceptions.MyAppException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Profile("cache")
public class CacheMatchPersistenceService implements MatchPersistence {
    private static final String MATCH_EXISTS_ERROR_MSG = "Match with id: %d already exists";
    Map<Integer, Match> matchMap = new HashMap<>();

    @Override
    public void save(Match match) {
        if (matchMap.containsKey(match.getId())) {
            throw new MyAppException(String.format(MATCH_EXISTS_ERROR_MSG, match.getId()));
        }
        matchMap.put(match.getId(), match);
    }
    @Override
    public void saveWithAdmin(String username, Match match) {
        if (matchMap.containsKey(match.getId())) {
            throw new MyAppException(String.format(MATCH_EXISTS_ERROR_MSG, match.getId()));
        }
        matchMap.put(match.getId(), match);
    }

    @Override
    public List<Match> getAllMatchesPossibleToBet() {
        return null;
    }

    @Override
    public List<Match> getAllMatchesByTeamId(Integer id) {
        return null;
    }

    @Override
    public List<Match> getMatchesByTeamIdWithinTimeRange(Integer id, LocalDateTime startTime, LocalDateTime endTime) {
        return null;
    }

    @Override
    public List<Match> getAllMatches() {
        return matchMap.values().stream().toList();
    }

    @Override
    public Optional<Match> getMatchById(Integer id) {
        if (!matchMap.containsKey(id)) {
            throw new MyAppException(String.format(MATCH_EXISTS_ERROR_MSG, id ));
        }
        return matchMap.values().stream()
                .filter(match -> match.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteMatchById(Integer id) {
        if (!matchMap.containsKey(id)) {
            throw new MyAppException(String.format(MATCH_EXISTS_ERROR_MSG, id));
        }
        matchMap.remove(id);
    }

    @Override
    public void updateMatchById(Integer id, Match match) {
        if (!matchMap.containsKey(id)) {
            throw new MyAppException(String.format(MATCH_EXISTS_ERROR_MSG, id));
        }
        matchMap.put(id, match);
    }
}
