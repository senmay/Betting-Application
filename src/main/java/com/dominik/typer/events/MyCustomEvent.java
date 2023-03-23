package com.dominik.typer.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class MyCustomEvent extends ApplicationEvent {

    @Getter
    private Integer value;

    public MyCustomEvent(Object source, Integer value) {
        super(source);
        this.value = value;
    }
}
