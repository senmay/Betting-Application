package com.dominik.typer.controller;

import com.dominik.typer.model.json.BetJson;
import com.dominik.typer.model.mapper.BetMapper;
import com.dominik.typer.service.betpersistence.BetService;
import com.dominik.typer.validators.GeneralValidator;
import com.dominik.typer.validators.ValidationGroupJson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bet")
@RequiredArgsConstructor
public class BetController {
    private final BetService betService;
    private final BetMapper betMapper;
    private final GeneralValidator validator;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createBet(@RequestBody BetJson betJson) {
        validator.validateObject(betJson, ValidationGroupJson.class);
        betService.saveBet(betMapper.mapFromBetJson(betJson));
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<BetJson> getBetsFromUser(@RequestHeader("login") String username) {
        return betMapper.mapToListBetJson(betService.getBetsFromUser(username));
    }
}
