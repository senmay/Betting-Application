package com.dominik.typer.repository;

import com.dominik.typer.model.exceptions.DbError;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DbErrorRepository extends JpaRepository<DbError, Integer> {
}
