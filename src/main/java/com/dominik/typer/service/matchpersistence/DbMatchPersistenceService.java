package com.dominik.typer.service.matchpersistence;

import com.dominik.typer.model.Match;
import com.dominik.typer.model.entity.MatchEntity;
import com.dominik.typer.model.exceptions.MyAppException;
import com.dominik.typer.model.mapper.MatchMapper;
import com.dominik.typer.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Profile({"db", "!cache"})
@RequiredArgsConstructor
public class DbMatchPersistenceService implements MatchPersistence {
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    @Override
    public void save(Match match) {
        matchRepository.save(matchMapper.mapToEntity(match));
    }

    @Override
    public void saveWithAdmin(Match match) {
        matchRepository.save(matchMapper.mapToEntity(match));
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
    public List<Match> getMatchesByTeamIdWithinTimeRange(Integer id, LocalDateTime startTime, LocalDateTime endTime) {
        List<MatchEntity> matchEntityList = matchRepository.findMatchesForTeamWithinTimeRange(id, startTime, endTime);
        return matchMapper.mapToListMatch(matchEntityList);
    }

    @Override
    public List<Match> getAllMatchesByTeamId(Integer id) {
        List<MatchEntity> matchEntityList = matchRepository.getAllMatchesByTeamId(id);
        return matchMapper.mapToListMatch(matchEntityList);
    }

    @Override
    public Optional<Match> getMatchById(Integer id) {
        Optional<MatchEntity> matchEntity = matchRepository.findById(id);
        return matchEntity.map(matchMapper::mapFromEntity);
    }

    @Override
    public void deleteMatchById(Integer id) {
        if (!matchRepository.existsById(id)) {
            throw new MyAppException("Match with id: " + id + " does not exist");
        }
        matchRepository.deleteById(id);
    }

}

