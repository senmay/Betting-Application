package com.dominik.typer.controller;

import com.dominik.typer.model.exceptions.DbError;
import com.dominik.typer.repository.DbErrorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/error")
public class DbErrorController {
    private final DbErrorRepository dbErrorRepository;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<DbError> getAllErrors()
    {
        return dbErrorRepository.findAll();
    }

}
