package com.dominik.typer.service.teampersistence;

import com.dominik.typer.model.Team;
import com.dominik.typer.model.exceptions.MyAppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamPersistence teamPersistence;

    public Team getTeamById(Integer id) {
        return teamPersistence.getTeamById(id);
    }

    public Optional<Team> getTeamByName(String name) {
        return teamPersistence.getTeamByName(name);
    }

    public void saveTeam(Team team) {
        checkForDuplicateTeam(team);
        teamPersistence.saveTeam(team);
    }
    public boolean deleteTeam(String name) {
        return teamPersistence.deleteTeamByName(name);
    }

    public List<Team> getTeams() {
        return teamPersistence.getAllTeams();
    }

    public void updateTeam(String name, Team team) {
        teamPersistence.updateTeamByName(name, team);
    }

    private void checkForDuplicateTeam(Team team) {
        Optional<Team> teamFromDb = teamPersistence.getTeamByName(team.getName());
        if (teamFromDb.isPresent()) {
            throw new MyAppException("Team with this name already exists");

        }
    }
}

