package com.dominik.typer.model.mapper;

import DataForTests.ProvideBet;
import com.dominik.typer.model.Bet;
import com.dominik.typer.model.entity.BetEntity;
import com.dominik.typer.model.json.BetJson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class BetMapperTest implements ProvideBet {
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
        Bet bet = provideBet();
        //when
        BetJson betJson = betMapper.mapFromBet(bet);
        //then
        assertThat(betJson)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }
    @Test
    void givenBet_whenMapToBetEntity_returnBetEntity() {
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
    void givenListOfBet_whenMapToListBetJson_returnListOfBetJson() {
        //given
        List<Bet> betList = provideBetList();
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
        List<BetJson> betJsonList = provideBetJsonList();
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
        List<BetEntity> betListEntity = provideBetEntityList();
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
        List<Bet> betList = provideBetList();
        //when
        List<BetEntity> betListEntity = betMapper.mapToListBetEntity(betList);
        //then
        assertThat(betListEntity)
                .isNotNull()
                .hasSize(10);
    }
}
