package com.dominik.typer.service;

import com.dominik.typer.model.Match;
import com.dominik.typer.model.Team;
import com.dominik.typer.model.User;
import com.dominik.typer.service.matchpersistence.MatchPersistance;
import com.dominik.typer.service.teampersistence.TeamPersistence;
import com.dominik.typer.service.userpersistence.UserPersistence;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

        User admin = User.builder().id(1).username("admin").email("admin.wp.pl").points(999).balance(BigDecimal.valueOf(100)).build();
        User user = User.builder().id(2).username("user").email("user.wp.pl").points(10).balance(BigDecimal.valueOf(100)).build();
        userPersistence.saveAdmin(admin);
        userPersistence.saveWithAdmin(admin.getUsername(), user);
        Team legia = Team.builder().id(1).name("legia").inactive(false).build();
        Team wisla = Team.builder().id(2).name("wisla").inactive(false).build();
        teamPersistence.saveTeam(legia);
        teamPersistence.saveTeam(wisla);
        Match match1 = Match.builder().id(1).homeTeamId(1)
                .awayTeamId(2).oddsForAwayTeam(1.00).oddsForHomeTeam(1.00)
                .oddsForDraw(1.00).dateOfEvent(LocalDateTime.now().plusDays(1))
                .matchResultId(0).build();
        Match match2 = Match.builder().id(2).homeTeamId(2)
                .awayTeamId(1).oddsForAwayTeam(3.00).oddsForHomeTeam(1.00)
                .oddsForDraw(1.00).dateOfEvent(LocalDateTime.now().plusDays(2))
                .matchResultId(0).build();
        matchPersistence.save(match1);
        matchPersistence.save(match2);
        System.out.println("Loaded data into database.");


    }
}
