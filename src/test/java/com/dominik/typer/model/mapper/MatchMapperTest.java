package com.dominik.typer.model.mapper;

import DataForTests.ProvideMatch;
import com.dominik.typer.model.Match;
import com.dominik.typer.model.json.MatchJson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MatchMapperTest implements ProvideMatch {
    MatchMapper matchMapper = new MatchMapperImpl();
    @Test
    void givenMatchJson_whenMapFromJson_returnMatch() {
        MatchJson matchJson = provideMatchJson();
        //when
        Match match = matchMapper.mapFromJson(matchJson);
        //then
        assertThat(match)
                .isNotNull()
                .hasNoNullFieldsOrProperties();

    }
    @Test
    void givenMatch_whenMapToJson_returnMatchJson() {
        Match match = provideMatch();
        //when
        MatchJson matchJson = matchMapper.map(match);
        //then
        assertThat(matchJson)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }
    @Test
    void givenListOfMatch_whenMapToListJson_returnListOfMatchJson() {
        List<Match> matchList = provideMatchListOf10lements();
        //when
        List<MatchJson> matchJsonList = matchMapper.mapToList(matchList);
        //then
        assertThat(matchJsonList)
                .isNotNull()
                .hasSize(10);
    }
    @Test
    void givenListOfMatchEntities_whenMapToListMatch_returnListOfMatch() {
        List<Match> matchList = provideMatchListOf10lements();
        //when
        List<MatchJson> matchJsonList = matchMapper.mapToList(matchList);
        //then
        assertThat(matchJsonList)
                .isNotNull()
                .hasSize(10);
    }

}