package com.dominik.typer.model.mapper;

import com.dominik.typer.model.Team;
import com.dominik.typer.model.entity.TeamEntity;
import com.dominik.typer.model.json.TeamJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;
@Mapper
public interface TeamMapper {
    @Mapping(target="inactive", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, defaultValue = "false")
    Team mapFromJson(TeamJson teamJsonJson);
    TeamJson mapToJson(Team team);
    Team mapFromTeamEntity(TeamEntity teamEntityEntity);
    TeamEntity mapToTeamEntity(Team team);
    List<TeamEntity> mapToListTeamEntity(List<Team> teams);
    List<Team> mapFromListTeamEntity(List<TeamEntity> teamEntities);

    List<Team> mapFromListJson(List<TeamJson> teamJsonList);
    List<TeamJson> mapToListTeamJson(List<Team> teamList);

}
