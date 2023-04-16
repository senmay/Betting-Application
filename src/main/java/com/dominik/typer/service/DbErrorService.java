package com.dominik.typer.service;

import com.dominik.typer.model.exceptions.DbError;
import com.dominik.typer.repository.DbErrorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class DbErrorService {

    private final DbErrorRepository dbErrorRepository;

    @Async
    public CompletableFuture<Void> saveAsync(DbError dbError) {
        log.error("saved!");
        return CompletableFuture.completedFuture(null);
    }
}


