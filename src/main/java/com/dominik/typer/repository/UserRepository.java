package com.dominik.typer.repository;

import com.dominik.typer.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

        Optional<UserEntity> getByUsername(String username);
}
