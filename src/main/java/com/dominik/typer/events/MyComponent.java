package com.dominik.typer.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyComponent {

//    @EventListener
//    public void a1(ApplicationEvent applicationEvent) {
//        System.out.println("-------------> : " + applicationEvent);
//    }

    @Async
    public void recalculate() {
        ////
    }

    @EventListener
    public void a2(ApplicationEvent applicationEvent) {
        System.out.println("-------------> : " + applicationEvent);
    }

    @EventListener(value = ApplicationReadyEvent.class)
    public void a(ApplicationReadyEvent applicationEvent) {
        System.out.println("-------------> : " + applicationEvent);
    }

    @EventListener
    @Async
//    @EventListener(value = MyCustomEvent.class)
    public void a4(MyCustomEvent myCustomEvent) {
        log.error("Custom event received");
        System.out.println("MyCustomEvent " + myCustomEvent);
        System.out.println(myCustomEvent.getValue());
        System.out.println(myCustomEvent.getSource());
    }
}
