package com.dominik.typer.repository;

import com.dominik.typer.model.exceptions.JsonError;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JsonErrorRepository extends JpaRepository<JsonError, Integer> {
    
}
