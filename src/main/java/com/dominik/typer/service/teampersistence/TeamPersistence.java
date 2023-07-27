package com.dominik.typer.service.teampersistence;

import com.dominik.typer.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamPersistence {
    void saveTeam(Team team);
    Team getTeamById(Integer id);
    Optional<Team> getTeamByName(String name);
    List<Team> getAllTeams();
    boolean deleteTeamByName(String name);
    void updateTeamByName(String name, Team team);
    void saveAllTeams(List<Team> teams);
}
