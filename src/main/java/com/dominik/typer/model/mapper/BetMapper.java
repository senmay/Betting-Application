package com.dominik.typer.model.mapper;

import com.dominik.typer.model.Bet;
import com.dominik.typer.model.entity.BetEntity;
import com.dominik.typer.model.json.BetJson;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BetMapper {

    BetJson mapFromBet(Bet bet);
    Bet mapFromBetJson(BetJson betJson);
    List<Bet> mapFromListBetJson(List<BetJson> betJsonList);
    List<BetJson> mapToListBetJson(List<Bet> betList);
    Bet mapFromBetEntity(BetEntity betEntity);
    BetEntity mapToBetEntity(Bet bet);
    List<Bet> mapToListBet(List<BetEntity> betEntities);
    List<BetEntity> mapToListBetEntity(List<Bet> bets);
}
