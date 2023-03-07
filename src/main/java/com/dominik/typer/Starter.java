package com.dominik.typer;

import com.dominik.typer.configuration.BeanConfig;
import com.dominik.typer.configuration.UserPagesize;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Starter {

    @Autowired
    private BeanConfig beanConfig;

    @Autowired
    private BeanConfig config;

    @Autowired
    private UserPagesize pagesize;

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }

    @PostConstruct
    public void method() {

    }
}
