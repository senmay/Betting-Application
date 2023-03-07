package com.dominik.typer.service.teamPersistence;

import com.dominik.typer.model.Team;
import com.dominik.typer.model.entity.TeamEntity;
import com.dominik.typer.model.mapper.TeamMapper;
import com.dominik.typer.repository.TeamRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Profile({"db", "!cache"})
public class DbTeamPersistenceService implements TeamPersistence {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public DbTeamPersistenceService(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public void saveTeam(Team team) {
        if (teamRepository.existsByName(team.getName())) {
            throw new RuntimeException("Team with name " + team.getName() + " already exists");
        }
        teamRepository.save(teamMapper.mapToTeamEntity(team));
    }

    @Override
    public Team getTeamByName(String name) {
        Optional<TeamEntity> byName = teamRepository.getByName(name);
        return byName.map(teamMapper::mapFromTeamEntity).orElseThrow(() -> new RuntimeException("No team with this name"));
//        TeamEntity team = new TeamEntity();
//        team.setName(name);
//        Example<TeamEntity> example = Example.of(team);
//        Optional<TeamEntity> one = teamRepository.findOne(example);
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
}

