package com.dominik.typer.service.matchpersistence;

import com.dominik.typer.model.Match;
import com.dominik.typer.model.Team;
import com.dominik.typer.model.exceptions.MyAppException;
import com.dominik.typer.service.adminpersistence.AdminService;
import com.dominik.typer.service.teampersistence.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "matches")
public class MatchService {
    private final MatchPersistence matchPersistence;
    private final TeamService teamService;
    private final AdminService adminService;

    public void saveMatchWithAdmin(String username, Match match) {
        adminService.isAdmin(username);
        validateTeamsExists(match.getHomeTeamId(), match.getAwayTeamId());
        canScheduleMatch(match.getHomeTeamId(), match.getAwayTeamId(), match.getDateOfEvent());
        matchPersistence.saveWithAdmin(username, match);
    }
    @CacheEvict(allEntries = true)
    public void deleteMatch(Integer id) {
        validateTeamExists(id);
        matchPersistence.deleteMatchById(id);
    }
    @Cacheable(key = "#id")
    public Optional<Match> getMatch(Integer id) {
        return matchPersistence.getMatchById(id);
    }

    @Cacheable
    public List<Match> getMatches() {
        return matchPersistence.getAllMatches();
    }
    @CachePut(key = "#id")
    public void updateMatch(Integer id, Match match) {
        matchPersistence.updateMatchById(id, match);
    }
    @Cacheable
    public List<Match> getMatchesPossibleToBet() {
        return matchPersistence.getAllMatchesPossibleToBet();
    }
    @Cacheable(key = "#id")
    public List<Match> getMatchesByTeamId(Integer id){
        return matchPersistence.getAllMatchesByTeamId(id);
    }

    private void validateTeamsExists(Integer homeTeamId, Integer awayTeamId) {
        //todo use existsById
        Team homeTeam = teamService.getTeamById(homeTeamId);
        Team awayTeam = teamService.getTeamById(awayTeamId);
        if (homeTeam == null || awayTeam == null) {
            throw new MyAppException("Team does not exist");
        }
    }
    private void validateTeamExists(Integer teamId){
        Team team = teamService.getTeamById(teamId);
        if (team == null) {
            throw new MyAppException("Team does not exist");
        }
    }
    //check if teams are playing in the same time in 2 hours time frame
    private boolean canScheduleMatch(Integer homeTeam, Integer awayTeam, LocalDateTime startTime) {
        LocalDateTime endTime = startTime.plusHours(2);
        List<Match> homeTeamMatches = matchPersistence.getMatchesByTeamIdWithinTimeRange(homeTeam, startTime, endTime);
        List<Match> awayTeamMatches = matchPersistence.getMatchesByTeamIdWithinTimeRange(awayTeam, startTime, endTime);
        if (!homeTeamMatches.isEmpty() || !awayTeamMatches.isEmpty()) {
            throw new MyAppException("Team is already playing in this time");
        }
        return true;
    }
}
