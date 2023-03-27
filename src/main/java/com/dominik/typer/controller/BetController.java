package com.dominik.typer.controller;

import com.dominik.typer.cachelessons.CacheService;
import com.dominik.typer.cachelessons.Person;
import com.dominik.typer.model.json.BetJson;
import com.dominik.typer.model.mapper.BetMapper;
import com.dominik.typer.service.betpersistence.BetService;
import com.dominik.typer.validators.GeneralValidator;
import com.dominik.typer.validators.ValidationGroupJson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bet")
@RequiredArgsConstructor
@Slf4j
public class BetController {
    private final BetService betService;
    private final BetMapper betMapper;
    private final GeneralValidator validator;

    private final CacheService cacheService;

    private final CacheManager cacheManager;

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

    @GetMapping("/test")
    int a() {
        Cache persons = cacheManager.getCache("persons");
        log.warn("pc-1");
        Person mat = cacheService.getPersonByName("Mat");
        log.warn("pc-2");
        Person mat2 = cacheService.getPersonByName("Mat");
        System.out.println(mat == mat2);
        System.out.println(mat.equals(mat2));
        log.warn("pc-3");
        Person p3 = cacheService.getPersonByName("Adam");
        log.warn("pc-4");
        return 1;
    }
}
