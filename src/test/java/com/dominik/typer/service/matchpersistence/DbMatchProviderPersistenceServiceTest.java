package com.dominik.typer.service.matchpersistence;


import com.dominik.typer.DataForTests.MatchProvider;
import com.dominik.typer.model.Match;
import com.dominik.typer.model.entity.MatchEntity;
import com.dominik.typer.model.exceptions.MyAppException;
import com.dominik.typer.model.mapper.MatchMapperImpl;
import com.dominik.typer.model.mapper.MatchMapper;
import com.dominik.typer.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbMatchProviderPersistenceServiceTest implements MatchProvider {
    @Mock
    private MatchRepository matchRepository;
    @Spy
    MatchMapper matchMapper = new MatchMapperImpl();
    @InjectMocks
    private DbMatchPersistenceService dbMatchPersistenceService;
    @Captor
    ArgumentCaptor<MatchEntity> entityCaptor;

    @Test
    void givenAdminUserAndMatch_whenSaveWithAdmin_thenReturnCheckIfSaved() {
        //given
        Match match = provideMatch();
        //when
        dbMatchPersistenceService.saveWithAdmin(match);
        //then
        then(matchRepository).should().save(entityCaptor.capture());
    }

    @Test
    void givenListOfMatches_whenGetAllMatches_thenReturnListOfMatches() {
        //given
        List<Match> matchList = provideMatchList(10);
        List<MatchEntity> matchEntities = matchMapper.mapToListEntity(matchList);
        given(matchRepository.findAll()).willReturn(matchEntities);
        //when
        List<Match> matches = dbMatchPersistenceService.getAllMatches();
        //then
        then(matchRepository).should().findAll();
        assertThat(matches)
                .isNotNull()
                .isNotEmpty()
                .hasSize(10);
    }

    @Test
    void givenListOfMatches_whenGetAllMatchesPossibleToBet_thenReturnListOfMatches() {
        //given
        List<Match> matchesWithFutureDate = provideMatchesWithDateInFuture(20);
        List<MatchEntity> matchesWithFutureDateEntity = matchMapper.mapToListEntity(matchesWithFutureDate);
        given(matchRepository.getAllByDateOfEventAfterNow()).willReturn(matchesWithFutureDateEntity);
        //when
        List<Match> matches = dbMatchPersistenceService.getAllMatchesPossibleToBet();
        //then
        then(matchRepository).should().getAllByDateOfEventAfterNow();
        assertThat(matches)
                .isNotNull()
                .isNotEmpty()
                .hasSize(20);
    }


    @Test
    void givenValidMatchId_whenDeleteMatchById_thenMatchIsDeleted() {
        //given
        Integer matchId = 1;
        when(matchRepository.existsById(matchId)).thenReturn(true);

        //when
        dbMatchPersistenceService.deleteMatchById(matchId);

        //then
        verify(matchRepository, times(1)).existsById(matchId);
        verify(matchRepository, times(1)).deleteById(matchId);
    }

    @Test
    void givenInvalidMatchId_whenDeleteMatchById_thenThrowMyAppException() {
        //given
        Integer matchId = 2;
        when(matchRepository.existsById(matchId)).thenReturn(false);

        //when
        MyAppException exception = assertThrows(MyAppException.class,
                () -> dbMatchPersistenceService.deleteMatchById(matchId));

        //then
        verify(matchRepository, times(1)).existsById(matchId);
        assertEquals("Match with id: " + matchId + " does not exist", exception.getMessage());
    }

    @Test
    public void givenNoData_whenGetAllMatches_thenReturnEmptyList() {
        // when
        List<Match> allMatches = dbMatchPersistenceService.getAllMatches();

        // then
        assertThat(allMatches)
                .hasSize(0)
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void givenMatches_whenGetMatchesByTeamIdWithinTimeRange() {
        //given
        Integer teamId = 1;
        LocalDateTime startTime = LocalDateTime.now().minusDays(7);
        LocalDateTime endTime = LocalDateTime.now();

        List<Match> expectedMatches = provideMatchList(2);
        List<MatchEntity> matchEntities = matchMapper.mapToListEntity(expectedMatches);

        when(matchRepository.findMatchesForTeamWithinTimeRange(teamId, startTime, endTime)).thenReturn(matchEntities);
        //when
        List<Match> actualMatches = dbMatchPersistenceService.getMatchesByTeamIdWithinTimeRange(teamId, startTime, endTime);

        //then
        verify(matchRepository, times(1)).findMatchesForTeamWithinTimeRange(teamId, startTime, endTime);
        assertEquals(expectedMatches, actualMatches);
    }

    @Test
    public void givenData_whenGetAllMatchesByTeamId_thenReturnListOfMatches() {
        //given
        Integer teamId = 1;
        List<Match> expectedMatches = provideMatchList(2);
        List<MatchEntity> matchEntities = matchMapper.mapToListEntity(expectedMatches);

        when(matchRepository.getAllMatchesByTeamId(teamId)).thenReturn(matchEntities);
        //when
        List<Match> actualMatches = dbMatchPersistenceService.getAllMatchesByTeamId(teamId);

        //then
        verify(matchRepository, times(1)).getAllMatchesByTeamId(teamId);
        assertEquals(expectedMatches, actualMatches);
    }

    @Test
    public void givenMatch_whenGetMatchById_thenReturnMatch() {
        //given
        Integer matchId = 1;
        Match expectedMatch = provideMatch();
        MatchEntity matchEntity = matchMapper.mapToEntity(expectedMatch);

        when(matchRepository.findById(matchId)).thenReturn(Optional.ofNullable(matchEntity));
        //when
        Optional<Match> actualMatch = dbMatchPersistenceService.getMatchById(matchId);

        //then
        verify(matchRepository, times(1)).findById(matchId);
        assertEquals(expectedMatch, actualMatch.get());
    }

}