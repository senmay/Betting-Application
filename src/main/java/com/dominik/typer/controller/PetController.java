package com.dominik.typer.controller;

import com.dominik.typer.model.json.PetJson;
import com.dominik.typer.validators.GeneralValidator;
import com.dominik.typer.validators.ValidationGroupBusinessLogic;
import com.dominik.typer.validators.ValidationGroupJson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {
    private final GeneralValidator validator;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PetJson createBet(@RequestBody PetJson petJson) {
        validator.validateObject(petJson, ValidationGroupJson.class, ValidationGroupBusinessLogic.class);
        return petJson;
    }
}
