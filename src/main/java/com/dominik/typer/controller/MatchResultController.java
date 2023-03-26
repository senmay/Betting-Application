package com.dominik.typer.controller;

import com.dominik.typer.model.json.MatchResultJson;
import com.dominik.typer.model.mapper.MatchResultMapper;
import com.dominik.typer.service.matchresultpersistence.MatchResultService;
import com.dominik.typer.validators.GeneralValidator;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class MatchResultController {
    private final MatchResultService matchResultService;
    private final GeneralValidator validator;
    private final MatchResultMapper matchResultMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void saveMatchResult(@RequestBody MatchResultJson matchResultJson) {
        validator.validateObject(matchResultJson, Default.class);
        matchResultService.saveMatchResult(matchResultMapper.mapFromMatchResultJson(matchResultJson));
    }
    @GetMapping("/{matchId}")
    @ResponseStatus(HttpStatus.OK)
    MatchResultJson getMatchResult(@PathVariable("matchId") Integer matchId) {
        matchResultService.getMatchResult(matchId);
        return matchResultMapper.mapToJson(matchResultService.getMatchResult(matchId));
    }
}
