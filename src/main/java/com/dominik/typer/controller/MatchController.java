package com.dominik.typer.controller;

import com.dominik.typer.model.Match;
import com.dominik.typer.model.exceptions.DbError;
import com.dominik.typer.model.json.MatchJson;
import com.dominik.typer.model.mapper.MatchMapper;
import com.dominik.typer.model.oddsapi.OddsApiService;
import com.dominik.typer.service.DbErrorService;
import com.dominik.typer.service.matchpersistence.MatchService;
import com.dominik.typer.validators.GeneralValidator;
import com.dominik.typer.validators.ValidationGroupJson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;
    private final DbErrorService dbErrorService;
    private final MatchMapper matchMapper;
    private final GeneralValidator validator;
    private final OddsApiService oddsApiService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createMatchWithAdmin(@RequestBody MatchJson matchJson) {
        validator.validateObject(matchJson, ValidationGroupJson.class);
        matchService.saveMatchWithAdmin(matchMapper.mapFromJson(matchJson));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<MatchJson> getAllMatches() {
        List<Match> matchList = matchService.getMatches();
        return matchMapper.mapToList(matchList);
    }

    @GetMapping("/byTeam/{id}")
    @ResponseStatus(HttpStatus.OK)
    List<MatchJson> getAllMatchesByTeamId(@PathVariable Integer id) {
        List<Match> matchList = matchService.getMatchesByTeamId(id);
        return matchMapper.mapToList(matchList);
    }

    @GetMapping("/toBet")
    @ResponseStatus(HttpStatus.OK)
    List<MatchJson> getAllMatchesPossibleToBet() {
        List<Match> matchList = matchService.getMatchesPossibleToBet();
        return matchMapper.mapToList(matchList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Optional<MatchJson> getMatch(@PathVariable Integer id) {
        Optional<Match> match = matchService.getMatch(id);
        return match.map(matchMapper::map);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteMatch(@PathVariable Integer id) {
        matchService.deleteMatch(id);
    }

    @PostMapping("/api/parse")
    @ResponseStatus(HttpStatus.OK)
        // if you want to parse matches from championship league, you should pass "CL" as league parameter
    void parseUpcomingMatchesFromApi(@RequestParam String league) {
        oddsApiService.fetchUpcomingMatchesData(league);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DbError handleRuntimeExceptionAsync(Exception exception) {
        DbError dbError = DbError.builder()
                .errorType(exception.getClass().getSimpleName())
                .errorMessage(exception.getMessage())
                .timestamp(LocalDateTime.now()).build();
        dbErrorService.saveAsync(dbError);
        return dbError;
    }
}
