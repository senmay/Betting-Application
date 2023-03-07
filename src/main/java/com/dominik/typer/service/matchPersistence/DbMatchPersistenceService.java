package com.dominik.typer.service.matchPersistence;

import com.dominik.typer.model.Match;
import com.dominik.typer.model.entity.MatchEntity;
import com.dominik.typer.model.mapper.MatchMapper;
import com.dominik.typer.repository.MatchRepository;
import com.dominik.typer.service.adminPersistence.AdminService;
import com.dominik.typer.service.teamPersistence.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile({"db", "!cache"})
@RequiredArgsConstructor
public class DbMatchPersistenceService implements MatchPersistance {
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final TeamService teamService;
    private final AdminService adminService;

    @Override
    public void save(Match match) {
        if (matchRepository.existsById(match.getId())) {
            throw new RuntimeException("Match with id: " + match.getId() + " already exists");
        }
        matchRepository.save(matchMapper.mapToEntity(match));
    }
    @Override
    public void saveWithAdmin(String username, Match match) {
        if(adminService.isAdmin(username)) {
            if (matchRepository.existsById(match.getId())) {
                throw new RuntimeException("Match with id: " + match.getId() + " already exists");
            }
            matchRepository.save(matchMapper.mapToEntity(match));
        }
    }

    @Override
    public List<Match> getAllMatches() {
        List<MatchEntity> matchEntityList = matchRepository.findAll();
        return matchMapper.mapToListMatch(matchEntityList);
    }

    @Override
    public List<Match> getAllMatchesPossibleToBet() {
        List<MatchEntity> matchEntityList = matchRepository.getAllByDateOfEventAfterNow();
        return matchMapper.mapToListMatch(matchEntityList);
    }

    @Override
    public Optional<Match> getMatchById(Integer id) {
//        Optional<MatchEntity> matchEntity = matchRepository.findById(id);
//        if (matchEntity.isPresent()) {
//            Team homeTeam = teamService.getTeamByName(matchEntity.get().getHomeTeamName());
//            Team awayTeam = teamService.getTeamByName(matchEntity.get().getAwayTeamName());
//            Match match = matchEntityMapper.map(matchEntity.get(), homeTeam, awayTeam);
//            return Optional.of(match);
//        }
        return null;
    }
//    }

        @Override
        public void deleteMatchById (Integer id){
            if (!matchRepository.existsById(id)) {
                throw new RuntimeException("Match with id: " + id + " does not exist");
            }
            matchRepository.deleteById(id);
        }

        @Override
        public void updateMatchById (Integer id, Match match){
        }
//        if (!matchRepository.existsById(id)) {
//            throw new RuntimeException("Match with id: " + id + " does not exist");
//        }
//        Optional<MatchEntity> matchEntity = matchRepository.findById(id);
//        Team homeTeam = match.getHomeTeam();
//        Team awayTeam = match.getAwayTeam();
//        Match updatedMatch = matchEntityMapper.map(matchEntity.get(), homeTeam, awayTeam);
//        updatedMatch.setHomeTeam(match.getHomeTeam());
//        updatedMatch.setAwayTeam(match.getAwayTeam());
//        updatedMatch.setHomeTeamGoals(match.getHomeTeamGoals());
//        updatedMatch.setAwayTeamGoals(match.getAwayTeamGoals());
//        matchRepository.save(matchEntityMapper.mapToMatchEntity(updatedMatch));
//    }
    }

