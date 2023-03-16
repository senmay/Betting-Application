package com.dominik.typer.controller;

import com.dominik.typer.model.json.PetJson;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {

    Validator validator;

    @Autowired
    public PetController(Validator validator) {
        this.validator = validator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PetJson createBet(@RequestBody PetJson petJson) {
        System.out.println(validator);

        Integer i = 10;
        val validate = validator.validate(petJson);
//        validator.validate(petJson, PetJson.ValidationGroupOne.class, PetJson.ValidationGroupTwo.class);
        System.out.println(validate);

        return petJson;
    }
}
