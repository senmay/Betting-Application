package com.dominik.typer;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.annotation.Annotation;
import java.util.Arrays;

@SpringBootApplication
@Data
public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }

    @PostConstruct
    public void method() {
        Annotation[] annotations = Starter.class.getAnnotations();
        System.out.println(Arrays.toString(annotations));
    }

    /**
     * Tworzysz użytkownika - Admin
     * Admin tworzy dwóch użytkowników
     * Admin tworzy dwie drużyny
     * admin tworzy Mecz
     * Użytkownik A = stawia mecz zwyciestwo 1
     * Użytkownik B = stawia mecz zwyciestwo 2
     * Admin konczy mecz (zwyciestwo 2)
     * ...
     * Uzytkownik A - nic nie dostaje
     * Uzytkownik B - dostaje kase, 1 pkt
     * ...
     */
}
