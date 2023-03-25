package com.dominik.typer.model.mapper;

import com.dominik.typer.DataForTests.BetProvider;
import com.dominik.typer.model.Bet;
import com.dominik.typer.model.entity.BetEntity;
import com.dominik.typer.model.json.BetJson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class TestBetMapperProvider implements BetProvider {
    BetMapper betMapper = new BetMapperImpl();
    @Test
    void givenBetJson_whenMapFromJson_returnBet() {
        //given
        BetJson betJson = provideBetJson();
        //when
        Bet bet = betMapper.mapFromBetJson(betJson);
        //then
        assertThat(bet)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void givenBet_whenMapToJson_returnBetJson() {
        //given
        Bet bet = provideBet();
        //when
        BetJson betJson = betMapper.mapFromBet(bet);
        //then
        assertThat(betJson)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void givenBetEntity_whenMapToBet_returnBet() {
        //given
        BetEntity betEntity = provideBetEntity();
        //when
        Bet bet = betMapper.mapFromBetEntity(betEntity);
        //then
        assertThat(bet)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }
    @Test
    void givenBet_whenMapToBetEntity_returnBetEntity() {
        //given
        Bet bet = provideBet();
        //when
        BetEntity betEntity = betMapper.mapToBetEntity(bet);
        //then
        assertThat(betEntity)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }
    @Test
    void givenListOfBet_whenMapToListBetJson_returnListOfBetJson() {
        //given
        List<Bet> betList = provideBetList(10);
        //when
        List<BetJson> betJsonList = betMapper.mapToListBetJson(betList);
        //then
        assertThat(betJsonList)
                .isNotNull()
                .hasSize(10);
    }
    @Test
    void givenListOfBetJson_whenMapToListBet_returnListOfBet() {
        //given
        List<BetJson> betJsonList = provideBetJsonList(10);
        //when
        List<Bet> betList = betMapper.mapFromListBetJson(betJsonList);
        //then
        assertThat(betList)
                .isNotNull()
                .hasSize(10);
    }
    @Test
    void givenListOfBetEntities_whenMapToListBet_returnListOfBet() {
        //given
        List<BetEntity> betListEntity = provideBetEntityList(10);
        //when
        List<Bet> betList = betMapper.mapToListBet(betListEntity);
        //then
        assertThat(betList)
                .isNotNull()
                .hasSize(10);
    }
    @Test
    void givenListOfBet_whenMapToListBetEntity_returnListOfBetEntity() {
        //given
        List<Bet> betList = provideBetList(10);
        //when
        List<BetEntity> betListEntity = betMapper.mapToListBetEntity(betList);
        //then
        assertThat(betListEntity)
                .isNotNull()
                .hasSize(10);
    }

}
