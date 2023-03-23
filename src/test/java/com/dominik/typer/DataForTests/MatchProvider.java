package com.dominik.typer.DataForTests;

import com.dominik.typer.model.Match;
import com.dominik.typer.model.json.MatchJson;
import com.dominik.typer.model.mapper.MatchMapper;
import com.dominik.typer.model.mapper.MatchMapperImpl;
import com.github.javafaker.Faker;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface MatchProvider {
    MatchMapper matchMapper = new MatchMapperImpl();

    default Match provideMatch() {
        Faker faker = new Faker();
        return Match.builder()
                .id(faker.number().numberBetween(1, 100))
                .homeTeamId(faker.number().numberBetween(1, 100))
                .awayTeamId(faker.number().numberBetween(1, 100))
                .dateOfEvent(faker.date().between(faker.date().past(100, TimeUnit.DAYS), faker.date().future(100, TimeUnit.DAYS)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .oddsForHomeTeam(faker.number().randomDouble(2, 1, 5))
                .oddsForDraw(faker.number().randomDouble(2, 1, 5))
                .oddsForAwayTeam(faker.number().randomDouble(2, 1, 5))
                .isFinished(faker.bool().bool())
                .matchResultId(faker.number().numberBetween(1, 100))
                .build();
    }

    default MatchJson provideMatchJson() {
        return matchMapper.map(provideMatch());
    }

    default List<Match> provideMatchList(Integer size) {
        List<Match> matchList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            matchList.add(provideMatch());
        }
        return matchList;
    }
    default List<Match> provideMatchListBetweenIds(Integer startId, Integer endId){
        List<Match> matchList = new ArrayList<>();
        for (int i = startId; i <= endId; i++) {
            Match matchToAdd = provideMatch();
            matchToAdd.setId(i);
            matchList.add(matchToAdd);
        }
        return matchList;
    }
    default List<Match> provideMatchesWithDateInFuture(Integer size){
        Faker faker = new Faker();
        List<Match> matchList = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            Match matchToAdd = provideMatch();
            matchToAdd.setDateOfEvent(faker.date().future(100, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            matchList.add(matchToAdd);
        }
        return matchList;
    }
    default List<Match> provideMatchesWithDateInPast(Integer size){
        Faker faker = new Faker();
        List<Match> matchList = new ArrayList<>();
        for(int i = 0; i < size; i++){
            Match matchToAdd = provideMatch();
            matchToAdd.setDateOfEvent(faker.date().past(100, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            matchList.add(matchToAdd);
        }
        return matchList;
    }
}
