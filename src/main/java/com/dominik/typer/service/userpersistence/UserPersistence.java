package com.dominik.typer.service.userpersistence;

import com.dominik.typer.model.User;

import java.util.List;
import java.util.Optional;

public interface UserPersistence {

    void saveAdmin(User user);
    void saveWithAdmin(String username, User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Integer id);
    void deleteUserById(Integer id);
    void updateUserById(Integer id, User user);
    Optional<User> getUserByUsername(String username);
    void updateBalance(Integer id, Double balance);
}
