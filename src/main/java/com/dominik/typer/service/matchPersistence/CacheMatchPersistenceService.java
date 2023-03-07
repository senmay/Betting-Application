package com.dominik.typer.service.matchPersistence;

import com.dominik.typer.model.Match;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
@Profile("cache")
public class CacheMatchPersistenceService implements MatchPersistance{
    Map<Integer, Match> matchMap = new HashMap<>();

    @Override
    public void save(Match match) {
        if (matchMap.containsKey(match.getId())) {
            throw new RuntimeException("Match with id: " + match.getId() + " already exists");
        }
        matchMap.put(match.getId(), match);
    }
    @Override
    public void saveWithAdmin(String username, Match match) {
        if (matchMap.containsKey(match.getId())) {
            throw new RuntimeException("Match with id: " + match.getId() + " already exists");
        }
        matchMap.put(match.getId(), match);
    }

    @Override
    public List<Match> getAllMatchesPossibleToBet() {
        return null;
    }

    @Override
    public List<Match> getAllMatches() {
        return matchMap.values().stream().toList();
    }

    @Override
    public Optional<Match> getMatchById(Integer id) {
        if (!matchMap.containsKey(id)) {
            throw new RuntimeException("Match with id: " + id + " does not exist");
        }
        return matchMap.values().stream()
                .filter(match -> match.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteMatchById(Integer id) {
        if (!matchMap.containsKey(id)) {
            throw new RuntimeException("Match with id: " + id + " does not exist");
        }
        matchMap.remove(id);
    }

    @Override
    public void updateMatchById(Integer id, Match match) {
        if (!matchMap.containsKey(id)) {
            throw new RuntimeException("Match with id: " + id + " does not exist");
        }
        matchMap.put(id, match);
    }
}
