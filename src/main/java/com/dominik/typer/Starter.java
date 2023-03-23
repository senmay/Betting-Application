package com.dominik.typer;

import com.dominik.typer.events.MyCustomEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Starter {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void are() {
        System.out.println("Jestem w ApplicationReadyEvent");

        MyCustomEvent myCustomEvent = new MyCustomEvent(Starter.class, 123);
        applicationEventPublisher.publishEvent(myCustomEvent);
    }
}
