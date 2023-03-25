package com.dominik.typer.service.betpersistence;

import com.dominik.typer.model.Bet;
import com.dominik.typer.model.entity.BetEntity;
import com.dominik.typer.model.exceptions.MyAppException;
import com.dominik.typer.model.mapper.BetMapper;
import com.dominik.typer.repository.BetRepository;
import com.dominik.typer.repository.MatchRepository;
import com.dominik.typer.service.matchpersistence.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile({"db", "!cache"})
@RequiredArgsConstructor
public class DbBetPersistenceService implements BetPersistence {

    private final BetRepository betRepository;
    private final BetMapper betMapper;
    private final MatchRepository matchRepository;
    private final MatchService matchService;

    @Override
    public void saveBet(Bet bet) {
        betRepository.save(betMapper.mapToBetEntity(bet));
    }

    @Override
    public List<Bet> getAllBets() {
        List<BetEntity> betEntityList = betRepository.findAll();
        return betMapper.mapToListBet(betEntityList);
    }

    @Override
    public void deleteBetById(Integer id) {
        if (!matchRepository.existsById(id)) {
            throw new MyAppException("Match with id: " + id + " does not exist");
        }
        betRepository.deleteById(id);
    }

    @Override
    public List<Bet> getBetsFromUser(Integer id) {
        List<BetEntity> betEntityList = betRepository.findAllBetsByUserId(id);
        return betMapper.mapToListBet(betEntityList);
    }

    @Override
    public List<Bet> getBetsFromMatch(Integer id) {
        List<BetEntity> betEntityList = betRepository.findAllBetsByMatchId(id);
        return betMapper.mapToListBet(betEntityList);
    }
}
