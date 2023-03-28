package com.dominik.typer.cachelessons;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Slf4j
//@CacheConfig(cacheNames = {"persons"})
@Component
public class CacheService {

//    @SneakyThrows
    @Cacheable(value = "persons", key = "#name")
    public Person getPersonByName(String name) {
        log.warn("getPersonByName start");
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        val p = new Person(name, 20);
        log.warn("getPersonByName end");
        return p;
    }

    public Person getPerson() {
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        val p = new Person("zosia", 20);
        return p;
    }
}
