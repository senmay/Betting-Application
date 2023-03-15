package com.dominik.typer.service.teampersistence;

import com.dominik.typer.model.Team;

import java.util.List;

public interface TeamPersistence {
    void saveTeam(Team team);
    Team getTeamByName(String name);
    List<Team> getAllTeams();
    boolean deleteTeamByName(String name);
    void updateTeamByName(String name, Team team);
}
