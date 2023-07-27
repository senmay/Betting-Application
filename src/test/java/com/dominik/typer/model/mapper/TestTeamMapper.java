package com.dominik.typer.model.mapper;

import com.dominik.typer.model.Team;
import com.dominik.typer.model.csv.TeamCSV;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestTeamMapper {
    TeamMapper teamMapper = new TeamMapperImpl();

    @Test
    void givenListTeamCSV_whenMapFromCSV_thenListTeam() {
        TeamCSV team = new TeamCSV("amer");
        TeamCSV team1 = new TeamCSV("amera");
        List<TeamCSV> listTeam = List.of(team, team1);
        //when
        List<Team> listTeam1 = teamMapper.mapFromListCSV(listTeam);
        System.out.println(listTeam1.get(0).getInactive());
        //then
        assertThat(listTeam1)
                .hasSize(2)
                .isNotNull()
                .extracting(Team::getId, Team::getName, Team::getInactive)
                .doesNotContainNull();
    }
}
