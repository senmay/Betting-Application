package com.dominik.typer.service.userpersistence;

import com.dominik.typer.model.User;
import com.dominik.typer.service.teampersistence.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserPersistence userPersistence;
    private final TeamService teamService;
    public void saveUser(final User user) {
        userPersistence.save(user);
    }
    public void saveWithAdmin(String username, final User user) {
        userPersistence.saveWithAdmin(username, user);
    }
    public void deleteUser(final Integer id) {
        userPersistence.deleteUserById(id);
    }

    public User getUser(final Integer id) {
        return userPersistence.getUserById(id);
    }

    public List<User> getUsers() {
        return userPersistence.getAllUsers();
    }
    public void updateUser(Integer id, User user) {
        userPersistence.updateUserById(id, user);
    }
    public User getUserWithUsername(String username) {
        return userPersistence.getUserByUsername(username);
    }
}

