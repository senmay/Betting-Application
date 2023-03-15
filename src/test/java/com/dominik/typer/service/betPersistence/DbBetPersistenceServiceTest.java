package com.dominik.typer.service.betPersistence;

import DataForTests.ProvideBet;
import DataForTests.ProvideMatch;
import com.dominik.typer.configuration.MyAppProperties;
import com.dominik.typer.model.Bet;
import com.dominik.typer.model.Match;
import com.dominik.typer.model.entity.BetEntity;
import com.dominik.typer.model.exceptions.MyAppException;
import com.dominik.typer.model.mapper.BetMapper;
import com.dominik.typer.repository.BetRepository;
import com.dominik.typer.repository.MatchRepository;
import com.dominik.typer.service.matchPersistence.MatchService;
import com.dominik.typer.service.userPersistence.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DbBetPersistenceServiceTest implements ProvideBet, ProvideMatch {
    @Mock
    private BetRepository betRepository;
    @Mock
    private BetMapper betMapper;
    @Mock
    private UserService userService;
    @Mock
    private MatchService matchService;
    @InjectMocks
    private DbBetPersistenceService dbBetPersistenceService;

    @Test
    void givenBetWithCorrectMatchId_whenSaveBet_thenCheckIfSaved() {
        //given
        BetEntity betEntity = BetEntity.builder().id(1).matchId(5).build();
        Bet bet = Bet.builder().id(1).matchId(5).build();
        List<Match> matches = provideMatchListWithIdBetween5And10();
        given(matchService.getMatchesPossibleToBet()).willReturn(matches);
        given(betRepository.existsById(1)).willReturn(false);
        given(betMapper.mapToBetEntity(bet)).willReturn(betEntity);
        //when
        dbBetPersistenceService.saveBet(bet);
        //then
        then(betRepository).should().save(betEntity);
    }

    @Test
    void givenBetWithIncorrectMatchId_whenSaveBet_thenThrowException(){
        //given
        Bet bet = Bet.builder().id(1).matchId(3).build();
        BetEntity betEntity = BetEntity.builder().id(1).matchId(3).build();
        List<Match> matches = provideMatchListWithIdBetween5And10();
        given(matchService.getMatchesPossibleToBet()).willReturn(matches);
        //when & then
        assertThatThrownBy(() -> dbBetPersistenceService.saveBet(bet))
                .isInstanceOf(MyAppException.class)
                .hasMessage("Match with id: " + bet.getMatchId() + " does not exist");
    }



}