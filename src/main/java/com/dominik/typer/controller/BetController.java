package com.dominik.typer.controller;

import com.dominik.typer.model.json.BetJson;
import com.dominik.typer.model.mapper.BetMapper;
import com.dominik.typer.service.betpersistence.BetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bet")
@RequiredArgsConstructor
public class BetController {


    private final BetService betService;
    private final BetMapper betMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createBet(@RequestBody BetJson betJson) {
        betService.saveBet(betMapper.mapFromBetJson(betJson));
    }




}
