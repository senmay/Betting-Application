package com.dominik.typer.service.userPersistence;

import com.dominik.typer.model.User;

import java.util.List;
import java.util.Optional;

public interface UserPersistence {

    void save(User user);
    void saveWithAdmin(String username, User user);
    List<User> getAllUsers();
    User getUserById(Integer id);
    void deleteUserById(Integer id);
    void updateUserById(Integer id, User user);
    User getUserByUsername(String username);
}
