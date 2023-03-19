package com.dominik.typer.service.teampersistence;

import com.dominik.typer.model.Team;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Profile("cache")
@Service
public class CacheTeamPersistenceService implements TeamPersistence {
    private final Map<String, Team> teams = new HashMap<>();

    @Override
    public void saveTeam(Team team) {
        if (teams.containsKey(team.getName())) {
            throw new RuntimeException("Team with name " + team.getName() + " already exists");
        }
        teams.put(team.getName(), team);
    }

    @Override
    public Team getTeamById(Integer id) {
        return null;
    }

    @Override
    public Team getTeamByName(String name) {
        return teams.values().stream()
                .filter(team -> team.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No team with name " + name));
    }
    @Override
    public List<Team> getAllTeams() {
        return teams.values().stream().toList();
    }

    @Override
    public boolean deleteTeamByName(String name) {
        Optional<Team> teamToDelete = teams.values().stream()
                .filter(team -> team.getName().equals(name))
                .findFirst();
        if (teamToDelete.isPresent()) {
            teams.remove(teamToDelete.get().getName());
            return true;
        } else {
            throw new RuntimeException("No team with name " + name);
        }
    }
    @Override
    public void updateTeamByName(String name, Team team) {
        if (!teams.containsKey(name)) {
            throw new RuntimeException("No team with name " + name);
        }
        teams.put(name, team);
    }

}
