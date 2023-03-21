package com.dominik.typer.model.mapper;

import com.dominik.typer.model.Match;
import com.dominik.typer.model.entity.MatchEntity;
import com.dominik.typer.model.json.MatchJson;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MatchMapper {
    MatchJson map(Match match);
    Match mapFromJson(MatchJson matchJson);
    List<Match> mapFromListJson(List<MatchJson> matchJsonList);
    List<MatchJson> mapToList(List<Match> matchList);
    Match mapFromEntity(MatchEntity matchEntity);
    MatchEntity mapToEntity(Match match);
    List<Match> mapToListMatch(List<MatchEntity> matchEntities);
    List<MatchEntity> mapToListEntity(List<Match> matches);


}
