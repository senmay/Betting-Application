package com.dominik.typer.model.mapper;

import com.dominik.typer.model.MatchResult;
import com.dominik.typer.model.entity.MatchResultEntity;
import com.dominik.typer.model.json.MatchResultJson;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MatchResultMapper {
    MatchResultJson mapToJson(MatchResult matchResult);
    MatchResult mapFromMatchResultJson(MatchResultJson matchResultJson);
    MatchResult mapFromResultEntity(MatchResultEntity matchResult);
    MatchResultEntity mapToResultEntity(MatchResult matchResult);
    List<MatchResultEntity> mapToResultEntityList(List<MatchResult> matchResultList);
    List<MatchResult> mapToResultList(List<MatchResultEntity> matchResultEntityList);
    List<MatchResultJson> mapToJsonList(List<MatchResult> matchResultList);
}
