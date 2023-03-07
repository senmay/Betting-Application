package DataForTests;

import com.dominik.typer.model.Match;
import com.dominik.typer.model.json.MatchJson;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface ProvideMatch {

    default Match provideMatch() {
        Faker faker = new Faker();
        return Match.builder()
                .id(faker.number().numberBetween(1, 100))
                .homeTeamId(faker.number().numberBetween(1, 100))
                .awayTeamId(faker.number().numberBetween(1, 100))
                .dateOfEvent(faker.date().between(faker.date().past(100, TimeUnit.DAYS), faker.date().future(100, TimeUnit.DAYS)))
                .oddsForHomeTeam(faker.number().randomDouble(2, 1, 5))
                .oddsForDraw(faker.number().randomDouble(2, 1, 5))
                .oddsForAwayTeam(faker.number().randomDouble(2, 1, 5))
                .isFinished(faker.bool().bool())
                .matchResultId(faker.number().numberBetween(1, 100))
                .build();
    }

    default MatchJson provideMatchJson() {
        Faker faker = new Faker();
        return MatchJson.builder()
                .id(faker.number().numberBetween(1, 100))
                .homeTeamId(faker.number().numberBetween(1, 100))
                .awayTeamId(faker.number().numberBetween(1, 100))
                .dateOfEvent(faker.date().between(faker.date().past(100, TimeUnit.DAYS), faker.date().future(100, TimeUnit.DAYS)))
                .oddsForHomeTeam(faker.number().randomDouble(2, 1, 5))
                .oddsForDraw(faker.number().randomDouble(2, 1, 5))
                .oddsForAwayTeam(faker.number().randomDouble(2, 1, 5))
                .isFinished(faker.bool().bool())
                .matchResultId(faker.number().numberBetween(1, 100))
                .build();
    }

    default List<Match> provideMatchListOf10lements() {
        Faker faker = new Faker();
        List<Match> matchList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            matchList.add(Match.builder()
                    .id(faker.number().numberBetween(1, 100))
                    .homeTeamId(faker.number().numberBetween(1, 100))
                    .awayTeamId(faker.number().numberBetween(1, 100))
                    .dateOfEvent(faker.date().between(faker.date().past(100, TimeUnit.DAYS), faker.date().future(100, TimeUnit.DAYS)))
                    .oddsForHomeTeam(faker.number().randomDouble(2, 1, 5))
                    .oddsForDraw(faker.number().randomDouble(2, 1, 5))
                    .oddsForAwayTeam(faker.number().randomDouble(2, 1, 5))
                    .isFinished(faker.bool().bool())
                    .matchResultId(faker.number().numberBetween(1, 100))
                    .build());
        }
        return matchList;
    }
    default List<Match> provide20MatchesWithDateInFuture(){
        Faker faker = new Faker();
        List<Match> matchList = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            matchList.add(Match.builder()
                    .id(faker.number().numberBetween(1, 100))
                    .homeTeamId(faker.number().numberBetween(1, 100))
                    .awayTeamId(faker.number().numberBetween(1, 100))
                    .dateOfEvent(faker.date().future(100, TimeUnit.DAYS))
                    .oddsForHomeTeam(faker.number().randomDouble(2, 1, 5))
                    .oddsForDraw(faker.number().randomDouble(2, 1, 5))
                    .oddsForAwayTeam(faker.number().randomDouble(2, 1, 5))
                    .isFinished(faker.bool().bool())
                    .matchResultId(faker.number().numberBetween(1, 100))
                    .build());
        }
        return matchList;
    }
    default List<Match> provide15MatchesWithDateInPast(){
        Faker faker = new Faker();
        List<Match> matchList = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            matchList.add(Match.builder()
                    .id(faker.number().numberBetween(1, 100))
                    .homeTeamId(faker.number().numberBetween(1, 100))
                    .awayTeamId(faker.number().numberBetween(1, 100))
                    .dateOfEvent(faker.date().past(100, TimeUnit.DAYS))
                    .oddsForHomeTeam(faker.number().randomDouble(2, 1, 5))
                    .oddsForDraw(faker.number().randomDouble(2, 1, 5))
                    .oddsForAwayTeam(faker.number().randomDouble(2, 1, 5))
                    .isFinished(faker.bool().bool())
                    .matchResultId(faker.number().numberBetween(1, 100))
                    .build());
        }
        return matchList;
    }
}
