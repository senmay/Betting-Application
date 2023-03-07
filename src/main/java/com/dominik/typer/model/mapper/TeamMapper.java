package com.dominik.typer.model.mapper;

import com.dominik.typer.model.Team;
import com.dominik.typer.model.entity.TeamEntity;
import com.dominik.typer.model.json.TeamJson;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper
public interface TeamMapper {
    Team mapFromTeamEntity(TeamEntity teamEntityEntity);
    TeamEntity mapToTeamEntity(Team team);
    List<TeamEntity> mapToListTeamEntity(List<Team> teams);
    List<Team> mapFromListTeamEntity(List<TeamEntity> teamEntities);
    TeamJson mapToJson(Team team);
    Team mapFromJson(TeamJson teamJsonJson);
    List<Team> mapFromListJson(List<TeamJson> teamJsonList);
    List<TeamJson> mapToListTeamJson(List<Team> teamList);

}
