package com.dominik.typer.service.teamPersistence;

import com.dominik.typer.model.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamPersistence teamPersistence;
    public Team getTeamByName(String name) {
        return teamPersistence.getTeamByName(name);
    }
    public void saveTeam(Team team) {
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

}
