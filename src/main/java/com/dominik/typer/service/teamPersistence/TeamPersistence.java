package com.dominik.typer.service.teamPersistence;

import com.dominik.typer.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamPersistence {
    void saveTeam(Team team);
    Team getTeamByName(String name);
    List<Team> getAllTeams();
    boolean deleteTeamByName(String name);
    void updateTeamByName(String name, Team team);
}
