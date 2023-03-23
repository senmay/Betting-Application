package com.dominik.typer.service;

import com.dominik.typer.model.User;
import com.dominik.typer.service.matchpersistence.MatchPersistance;
import com.dominik.typer.service.teampersistence.TeamPersistence;
import com.dominik.typer.service.userpersistence.UserPersistence;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Profile("dummy")
@RequiredArgsConstructor
public class DummyDataLoader {
    private final UserPersistence userPersistence;
    private final TeamPersistence teamPersistence;
    private final MatchPersistance matchPersistence;

    @PostConstruct
    public void loadDummyData() {
//        Faker faker = new Faker();
//        List<Team> teams = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            User user = new User(i, faker.name().firstName(), faker.random().nextInt(1,100), faker.internet().emailAddress());
//            userPersistence.save(user);
//            Team team = new Team(faker.team().name());
//            teamPersistence.saveTeam(team);
//            teams.add(team);
//        }
//        for (int i = 0; i < 10; i++){
//            Match match = new Match(i, teams.get(i), teams.get(i+1), faker.random().nextInt(0,5), faker.random().nextInt(0,5));
//            matchPersistence.save(match);
//        }
        User admin = User.builder().id(1).username("admin").email("admin.wp.pl").points(999).balance(BigDecimal.valueOf(100)).build();
        User user = User.builder().id(2).username("user").email("user.wp.pl").points(10).balance(BigDecimal.valueOf(100)).build();
        userPersistence.save(admin);
        userPersistence.save(user);
        System.out.println("Loaded data into database.");

//        String[] activeProfiles = environment.getActiveProfiles();
//
//        if (Arrays.asList(activeProfiles).contains("db") || activeProfiles[0].equals("dummy")) {
//            userRepository.save(userEntityMapper.mapToUserEntity(Jas));
//            userRepository.save(userEntityMapper.mapToUserEntity(Malgosia));
//            ;
//        }
//
//        if (Arrays.asList(activeProfiles).contains("cache")) {
//            userCache.save(Jas);
//            userCache.save(Malgosia);
//            System.out.println("Loaded dummy data into cache.");
//        }
    }
}
