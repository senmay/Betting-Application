package com.dominik.typer.controller;

import com.dominik.typer.model.Team;
import com.dominik.typer.model.csv.TeamCSV;
import com.dominik.typer.model.exceptions.DbError;
import com.dominik.typer.model.json.TeamJson;
import com.dominik.typer.model.mapper.TeamMapper;
import com.dominik.typer.service.CSVService;
import com.dominik.typer.service.DbErrorService;
import com.dominik.typer.service.teampersistence.TeamService;
import com.dominik.typer.validators.GeneralValidator;
import com.dominik.typer.validators.ValidationGroupJson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final TeamMapper teamMapper;
    private final DbErrorService dbErrorService;
    private final GeneralValidator generalValidator;
    private final CSVService csvService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createTeam(@RequestBody TeamJson teamJson) {
        generalValidator.validateObject(teamJson, ValidationGroupJson.class);
        teamService.saveTeam(teamMapper.mapFromJson(teamJson));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TeamJson> getAllTeams() {
        List<Team> teamList = teamService.getTeams();
        return teamMapper.mapToListTeamJson(teamList);
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    Optional<TeamJson> getTeam(@PathVariable String name) {
        Optional<Team> team = teamService.getTeamByName(name);
        return team.map(teamMapper::mapToJson);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    void deleteTeam(@PathVariable String name) {
        teamService.deleteTeam(name);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    void updateTeam(@PathVariable String name, @RequestBody TeamJson teamJson) {
        teamService.updateTeam(name, teamMapper.mapFromJson(teamJson));
    }

    @PutMapping("/import")
    @ResponseStatus(HttpStatus.OK)
    void importTeamsFromGoogleSheets(@RequestParam String sheetTitle) throws IOException {
        List<TeamCSV> teams = csvService.getTeamsFromGoogleSheets(sheetTitle);
        teamService.importTeams(teamMapper.mapFromListCSV(teams));
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
