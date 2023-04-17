package com.dominik.typer.service;

import com.dominik.typer.model.Match;
import com.dominik.typer.model.Team;
import com.dominik.typer.model.User;
import com.dominik.typer.service.matchpersistence.MatchPersistence;
import com.dominik.typer.service.teampersistence.TeamPersistence;
import com.dominik.typer.service.userpersistence.UserPersistence;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Profile("dummy")
@RequiredArgsConstructor
public class DummyDataLoader {
    private final UserPersistence userPersistence;
    private final TeamPersistence teamPersistence;
    private final MatchPersistence matchPersistence;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadDummyData() {

        User admin = User.builder().id(1).username("admin").password(passwordEncoder.encode("superancko")).email("admin.wp.pl").points(999).balance(100.00).build();
        User user = User.builder().id(2).username("user").password(passwordEncoder.encode("dominiczek")).email("user.wp.pl").points(10).balance(100.00).build();
        userPersistence.saveAdmin(admin);
        userPersistence.saveWithAdmin(user);
        Team chelsea = Team.builder().id(1).name("Chelsea").inactive(false).build();
        Team real = Team.builder().id(2).name("Real Madrid").inactive(false).build();
        Team milan = Team.builder().id(3).name("AC Milan").inactive(false).build();
        Team napoli = Team.builder().id(4).name("Napoli").inactive(false).build();
        Team bayern = Team.builder().id(5).name("Bayern München").inactive(false).build();
        Team city = Team.builder().id(6).name("Manchester City").inactive(false).build();
        Team benfica = Team.builder().id(7).name("Benfica").inactive(false).build();
        Team inter = Team.builder().id(8).name("Internazionale Milano").inactive(false).build();
        List<Team> list = List.of(chelsea, real, milan, napoli, bayern, city, benfica, inter);
        for (Team team : list) {
            teamPersistence.saveTeam(team);
        }
        Match match1 = Match.builder().id(1).homeTeamId(1)
                .awayTeamId(2).oddsForAwayTeam(2.00).oddsForHomeTeam(3.00)
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
