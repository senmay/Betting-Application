package com.dominik.typer.service.matchresultpersistence;

import com.dominik.typer.model.MatchResult;
import com.dominik.typer.model.exceptions.MyAppException;
import com.dominik.typer.model.mapper.MatchResultMapper;
import com.dominik.typer.repository.BetRepository;
import com.dominik.typer.repository.MatchRepository;
import com.dominik.typer.repository.MatchResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchResultService {
    private final MatchResultRepository matchResultRepository;
    private final MatchRepository matchRepository;
    private final MatchResultMapper matchResultMapper;
    private final BetRepository betRepository;

    public void saveMatchResult(MatchResult matchResult) {
        Integer matchId = matchResult.getMatchId();
        checkIfMatchExists(matchId);
        checkIfMatchHasMatchResult(matchId);
        matchResultRepository.save(matchResultMapper.mapToResultEntity(matchResult));
    }

    public MatchResult getMatchResult(Integer matchId) {
        checkIfMatchExists(matchId);
        return matchResultMapper.mapFromResultEntity(matchResultRepository.getMatchResultEntityByMatchId(matchId)) ;
    }
    private void checkIfMatchHasMatchResult(Integer matchId) {
        if (matchResultRepository.existsByMatchId(matchId)) {
            throw new MyAppException("Match with id: " + matchId + " already has match result");
        }
    }
    private void checkIfMatchExists(Integer matchId) {
        if (!matchRepository.existsById(matchId)) {
            throw new MyAppException("Match with id: " + matchId + " not found");
        }
    }
}
