package DataForTests;

import com.dominik.typer.enumerations.BetType;
import com.dominik.typer.model.Bet;
import com.dominik.typer.model.entity.BetEntity;
import com.dominik.typer.model.json.BetJson;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public interface ProvideBet {

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
        Faker faker = new Faker();
        return BetJson.builder()
                .id(faker.number().numberBetween(1, 100))
                .matchId(faker.number().numberBetween(1, 100))
                .userId(faker.number().numberBetween(1, 100))
                .pointsFromBetEvent(faker.number().numberBetween(1, 3))
                .betType(faker.options().option(BetType.class))
                .betAmount(faker.number().numberBetween(5, 1000))
                .build();
    }

    default BetEntity provideBetEntity() {
        Faker faker = new Faker();
        return BetEntity.builder()
                .id(faker.number().numberBetween(1, 100))
                .matchId(faker.number().numberBetween(1, 100))
                .userId(faker.number().numberBetween(1, 100))
                .pointsFromBetEvent(faker.number().numberBetween(1, 3))
                .betType(faker.options().option(BetType.class))
                .betAmount(faker.number().numberBetween(5, 1000))
                .build();
    }

    default List<BetJson> provideBetJsonList() {
        Faker faker = new Faker();
        List<BetJson> betJsonList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            betJsonList.add(BetJson.builder()
                    .id(faker.number().numberBetween(1, 100))
                    .matchId(faker.number().numberBetween(1, 100))
                    .userId(faker.number().numberBetween(1, 100))
                    .pointsFromBetEvent(faker.number().numberBetween(1, 3))
                    .betType(faker.options().option(BetType.class))
                    .betAmount(faker.number().numberBetween(5, 1000))
                    .build());
        }
        return betJsonList;
    }
    default List<Bet> provideBetList(){
        Faker faker = new Faker();
        List<Bet> betList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            betList.add(Bet.builder()
                    .id(faker.number().numberBetween(1, 100))
                    .matchId(faker.number().numberBetween(1, 100))
                    .userId(faker.number().numberBetween(1, 100))
                    .pointsFromBetEvent(faker.number().numberBetween(1, 3))
                    .betType(faker.options().option(BetType.class))
                    .betAmount(faker.number().numberBetween(5, 1000))
                    .build());
        }
        return betList;
    }
    default List<BetEntity> provideBetEntityList(){
        Faker faker = new Faker();
        List<BetEntity> betEntityList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            betEntityList.add(BetEntity.builder()
                    .id(faker.number().numberBetween(1, 100))
                    .matchId(faker.number().numberBetween(1, 100))
                    .userId(faker.number().numberBetween(1, 100))
                    .pointsFromBetEvent(faker.number().numberBetween(1, 3))
                    .betType(faker.options().option(BetType.class))
                    .betAmount(faker.number().numberBetween(5, 1000))
                    .build());
        }
        return betEntityList;
    }
}
