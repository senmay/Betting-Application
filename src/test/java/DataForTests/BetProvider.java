package DataForTests;

import com.dominik.typer.enumerations.BetType;
import com.dominik.typer.model.Bet;
import com.dominik.typer.model.entity.BetEntity;
import com.dominik.typer.model.json.BetJson;
import com.dominik.typer.model.mapper.BetMapper;
import com.dominik.typer.model.mapper.BetMapperImpl;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public interface BetProvider {
    BetMapper betMapper = new BetMapperImpl();

    default Bet provideBet() {
        Faker faker = new Faker();
        return Bet.builder()
                .id(faker.number().numberBetween(1, 100))
                .matchId(faker.number().numberBetween(1, 100))
                .userId(faker.number().numberBetween(1, 100))
                .pointsFromBetEvent(faker.number().numberBetween(1, 3))
                .betType(faker.options().option(BetType.class))
                .betAmount(faker.number().numberBetween(5, 1000))
                .build();
    }

    default BetJson provideBetJson() {
        return betMapper.mapFromBet(provideBet());
    }

    default BetEntity provideBetEntity() {
        return betMapper.mapToBetEntity(provideBet());
    }

    default List<BetJson> provideBetJsonList(Integer size) {
        List<BetJson> betJsonList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            betJsonList.add(betMapper.mapFromBet(provideBet()));
        }
        return betJsonList;
    }
    default List<Bet> provideBetList(Integer size){
        List<Bet> betList = new ArrayList<>();
        for (int i = 0; i < size ; i++) {
            betList.add(provideBet());
        }
        return betList;
    }
    default List<BetEntity> provideBetEntityList(Integer size){
        List<BetEntity> betEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            betEntityList.add(betMapper.mapToBetEntity(provideBet()));
        }
        return betEntityList;
    }
}
