package com.dominik.typer.service.matchpersistence;


import com.dominik.typer.DataForTests.MatchProvider;
import com.dominik.typer.model.Match;
import com.dominik.typer.model.entity.MatchEntity;
import com.dominik.typer.model.mapper.MatchMapper;
import com.dominik.typer.model.mapper.MatchMapperImpl;
import com.dominik.typer.repository.MatchRepository;
import com.dominik.typer.service.adminpersistence.AdminService;
import com.dominik.typer.service.teampersistence.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbMatchProviderPersistenceServiceTest implements MatchProvider {
    @Mock
    private MatchRepository matchRepository;
    @Mock
    AdminService adminService;
    @Spy
    private MatchMapper matchMapper = new MatchMapperImpl();
    @Mock
    private TeamService teamService;
    @InjectMocks
    private DbMatchPersistenceService dbMatchPersistenceService;
    @Captor
    ArgumentCaptor<MatchEntity> entityCaptor;

    @Test
    void givenAdminUserAndMatch_whenSaveWithAdmin_thenReturnCheckIfSaved() {
        //given
        Match match = provideMatch();
        given(adminService.isAdmin("admin")).willReturn(true);
        //when
        dbMatchPersistenceService.saveWithAdmin("admin", match);
        //then
        then(matchRepository).should().save(entityCaptor.capture());
    }
    @Test
    void givenNotAdminUserAndMatch_whenSaveWithAdmin_thenDoNothing() {
        //given
        Match match = provideMatch();
        given(adminService.isAdmin("user")).willReturn(false);
        //when
        dbMatchPersistenceService.saveWithAdmin("user", match);
        //then
        then(matchRepository).should(never()).save(entityCaptor.capture());
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
        System.out.println(matchesWithFutureDate);
        List<MatchEntity> matchEntities = matchMapper.mapToListEntity(matchesWithFutureDate);
        given(matchRepository.getAllByDateOfEventAfterNow()).willReturn(matchEntities);
        //when
        List<Match> matches = dbMatchPersistenceService.getAllMatchesPossibleToBet();
        //then
        then(matchRepository).should().getAllByDateOfEventAfterNow();
        assertThat(matches)
                .isNotNull()
                .isNotEmpty()
                .hasSize(20);
    }
//
//
//    @Test
//    public void givenMatch_whenUpdateMatch_thenReturnUpdatedMatch() {
//        //given
//        Team team1 = Team.builder().name("Arsenal").build();
//        Team team2 = Team.builder().name("United").build();
//        Match match = Match.builder()
//                .id(1)
//                .homeTeam(team1)
//                .awayTeam(team2)
//                .homeTeamGoals(1)
//                .awayTeamGoals(2).build();
//        MatchEntity matchEntity = MatchEntity.builder()
//                .id(1)
//                .homeTeamName(team1.getName())
//                .awayTeamName(team2.getName())
//                .homeTeamGoals(0)
//                .awayTeamGoals(0)
//                .build();
//        Match updatedMatch = new Match();
//        MatchEntity matchEntity2 = MatchEntity.builder()
//                .id(1)
//                .homeTeamName(team1.getName())
//                .awayTeamName(team2.getName())
//                .homeTeamGoals(match.getHomeTeamGoals())
//                .awayTeamGoals(match.getAwayTeamGoals())
//                .build();
//
//        when(matchRepository.existsById(1)).thenReturn(true);
//        when(matchRepository.findById(1)).thenReturn(Optional.of(matchEntity));
//        when(matchEntityMapper.map(matchEntity, team1, team2)).thenReturn(updatedMatch);
//        when(matchEntityMapper.mapToMatchEntity(updatedMatch)).thenReturn(matchEntity2);
//
//        //when
//        dbMatchPersistenceService.updateMatchById(1, match);
//
//        //then
//        verify(matchRepository).existsById(1);
//        verify(matchRepository).findById(1);
//        verify(matchEntityMapper).map(matchEntity, team1, team2);
//        verify(matchEntityMapper).mapToMatchEntity(updatedMatch);
//        verify(matchRepository).save(entityCaptor.capture());
//        assertThat(entityCaptor.getValue().getId()).isEqualTo(match.getId());
//        assertThat(entityCaptor.getValue().getHomeTeamName()).isEqualTo(match.getHomeTeam().getName());
//        assertThat(entityCaptor.getValue().getAwayTeamName()).isEqualTo(match.getAwayTeam().getName());
//        assertThat(entityCaptor.getValue().getHomeTeamGoals()).isEqualTo(match.getHomeTeamGoals());
//        assertThat(entityCaptor.getValue().getAwayTeamGoals()).isEqualTo(match.getAwayTeamGoals());
//    }
//
//    @Test
//    @Disabled("zapytac o zwrot listy")
//    public void givenTwoMatches_whenDeleteMatchWithId1_thenReturnListWithOneElement() {
//        // given
//        MatchEntity match1 = new MatchEntity();
//        match1.setId(1);
//        MatchEntity match2 = new MatchEntity();
//        match2.setId(2);
//
//        when(matchRepository.findAll()).thenReturn(List.of(match1, match2));
//        when(matchRepository.existsById(1)).thenReturn(true);
//
//        // when
//        dbMatchPersistenceService.deleteMatchById(1);
//
//        // then
//        verify(matchRepository, times(1)).deleteById(1);
//        List<MatchEntity> updatedList = matchRepository.findAll();
//        assertThat(updatedList)
//                .hasSize(1)
//                .contains(match2);
//        assertThat(updatedList).doesNotContain(match1);
//    }
//
//    @Test
//    public void givenTwoMatches_whenDeleteMatchWithId1_thenDeleteMatchWithId1() {
//        //given
//        MatchEntity match1 = new MatchEntity();
//        match1.setId(1);
//        MatchEntity match2 = new MatchEntity();
//        match2.setId(2);
//        when(matchRepository.existsById(1)).thenReturn(true);
//
//        // when
//        dbMatchPersistenceService.deleteMatchById(1);
//
//        // then
//        verify(matchRepository, times(1)).deleteById(1);
//    }
//
//
//    @Test
//    public void givenMatches_whenSaveTwoMatch_thenReturnListWithTwoElements() {
//        //given
//        Team team1 = Team.builder().name("Arsenal").build();
//        Team team2 = Team.builder().name("United").build();
//        Match match1 = Match.builder()
//                .id(1)
//                .homeTeam(team1)
//                .awayTeam(team2)
//                .homeTeamGoals(1)
//                .awayTeamGoals(2)
//                .build();
//        Match match2 = Match.builder()
//                .id(2)
//                .homeTeam(team2)
//                .awayTeam(team1)
//                .homeTeamGoals(2)
//                .awayTeamGoals(1)
//                .build();
//        MatchEntity matchEntity1 = new MatchEntity();
//        MatchEntity matchEntity2 = new MatchEntity();
//
//        //when
//        when(matchEntityMapper.mapToMatchEntity(match1)).thenReturn(matchEntity1);
//        dbMatchPersistenceService.save(match1);
//        when(matchEntityMapper.mapToMatchEntity(match2)).thenReturn(matchEntity2);
//        dbMatchPersistenceService.save(match2);
//        verify(matchRepository, times(1)).save(matchEntity1);
//        verify(matchRepository, times(1)).save(matchEntity2);
//        when(matchRepository.findAll()).thenReturn(List.of(matchEntity1, matchEntity2));
//
//        List<Match> allMatches = dbMatchPersistenceService.getAllMatches();
//
//        //then
//        assertThat(allMatches)
//                .isNotNull()
//                .hasSize(2);
//    }
//
//    @Test
//    public void givenNoData_whenGetAllMatches_thenReturnEmptyList() {
//        // given
//
//        // when
//        List<Match> allMatches = dbMatchPersistenceService.getAllMatches();
//
//        // then
//        assertThat(allMatches)
//                .hasSize(0)
//                .isNotNull()
//                .isEmpty();
//    }
//
//    @Test
//    public void givenArsenalData_whenGetAllMatches_thenReturnArsenalMatch() {
//        // given
//        MatchEntity matchEntity = new MatchEntity();
//        matchEntity.setId(1);
//        matchEntity.setHomeTeamName("Arsenal");
//        matchEntity.setAwayTeamName("Arsenal");
//        matchEntity.setHomeTeamGoals(0);
//        matchEntity.setAwayTeamGoals(1);
//        when(matchRepository.findAll()).thenReturn(List.of(matchEntity));
//
//        Team arsenal = new Team("Arsenal");
//        when(teamService.getTeamByName("Arsenal")).thenReturn(arsenal);
//
//        // when
//        List<Match> result = dbMatchPersistenceService.getAllMatches();
//
//        // then
//        assertThat(result)
//                .hasSize(1)
//                .isNotNull();
//
//        Match match = result.get(0);
//
//        assertThat(match.getAwayTeamGoals())
//                .isEqualTo(1);
//    }
}