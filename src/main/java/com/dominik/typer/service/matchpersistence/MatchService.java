package com.dominik.typer.service.matchpersistence;

import com.dominik.typer.model.Match;
import com.dominik.typer.model.Team;
import com.dominik.typer.model.exceptions.MyAppException;
import com.dominik.typer.service.adminpersistence.AdminService;
import com.dominik.typer.service.teampersistence.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchPersistance matchPersistance;
    private final TeamService teamService;
    private final AdminService adminService;

    public void saveMatchWithAdmin(String username, Match match) {
        adminService.isAdmin(username);
        validateTeamsExists(match.getHomeTeamId(), match.getAwayTeamId());
        matchPersistance.saveWithAdmin(username, match);
    }

    public void deleteMatch(Integer id) {
        validateTeamExists(id);
        matchPersistance.deleteMatchById(id);
    }

    public Match getMatch(Integer id) {
        return matchPersistance.getMatchById(id);
    }

    public List<Match> getMatches() {
        return matchPersistance.getAllMatches();
    }

    public void updateMatch(Integer id, Match match) {
        matchPersistance.updateMatchById(id, match);
    }

    public List<Match> getMatchesPossibleToBet() {
        return matchPersistance.getAllMatchesPossibleToBet();
    }

    private void validateTeamsExists(Integer homeTeamId, Integer awayTeamId) {
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
}
