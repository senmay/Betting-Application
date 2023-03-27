package com.dominik.typer;

import com.dominik.typer.cachelessons.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationEventPublisher;

@SpringBootApplication
@EnableCaching
@Slf4j
public class Starter {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    CacheService cacheService;

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void are() {
//        System.out.println("Jestem w ApplicationReadyEvent");
//
//        MyCustomEvent myCustomEvent = new MyCustomEvent(Starter.class, 123);
//        applicationEventPublisher.publishEvent(myCustomEvent);
//    }
}
