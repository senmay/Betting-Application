package com.dominik.typer.service.teampersistence;

import com.dominik.typer.model.Team;
import com.dominik.typer.model.entity.TeamEntity;
import com.dominik.typer.model.exceptions.MyAppException;
import com.dominik.typer.model.mapper.TeamMapper;
import com.dominik.typer.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Profile({"db", "!cache"})
@RequiredArgsConstructor
public class DbTeamPersistenceService implements TeamPersistence {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    public void saveTeam(Team team) {
        teamRepository.save(teamMapper.mapToTeamEntity(team));
    }

    @Override
    public Team getTeamById(Integer id) {
        TeamEntity teamEntity = teamRepository.findById(id).orElseThrow(() -> new MyAppException("No team with this id"));
        return teamMapper.mapFromTeamEntity(teamEntity);
    }


    @Override
    public Optional<Team> getTeamByName(String name) {
        Optional<TeamEntity> byName = teamRepository.getByName(name);
        return byName.map(teamMapper::mapFromTeamEntity);
    }
    @Override
    public List<Team> getAllTeams() {
        return teamMapper.mapFromListTeamEntity(teamRepository.findAll());
    }

    @Override
    public boolean deleteTeamByName(String name) {
        if (teamRepository.existsByName(name)) {
            teamRepository.deleteByName(name);
            return true;
        }
        return false;
    }
    public boolean deleteById(Integer id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public void updateTeamByName(String name, Team team) {
        if (!teamRepository.existsByName(name)) {
            throw new RuntimeException("Team not found");
        }
        Optional<TeamEntity> teamEntity = teamRepository.findByName(name);
        teamEntity.get().setName(team.getName());
        teamRepository.save(teamEntity.get());
    }

    @Override
    public void saveAllTeams(List<Team> teams) {
        teamRepository.saveAll(teamMapper.mapToListTeamEntity(teams));
    }
}

