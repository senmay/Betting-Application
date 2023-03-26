package com.dominik.typer.model.mapper;

import com.dominik.typer.model.Bet;
import com.dominik.typer.model.entity.BetEntity;
import com.dominik.typer.model.json.BetJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper
public interface BetMapper {
    @Mapping(target ="pointsFromBetEvent", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, defaultValue = "0")
    BetJson mapFromBet(Bet bet);
    Bet mapFromBetJson(BetJson betJson);
    List<Bet> mapFromListBetJson(List<BetJson> betJsonList);
    List<BetJson> mapToListBetJson(List<Bet> betList);
    Bet mapFromBetEntity(BetEntity betEntity);
    BetEntity mapToBetEntity(Bet bet);
    List<Bet> mapToListBet(List<BetEntity> betEntities);
    List<BetEntity> mapToListBetEntity(List<Bet> bets);
}
