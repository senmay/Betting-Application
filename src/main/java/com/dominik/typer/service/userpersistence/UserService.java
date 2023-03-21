package com.dominik.typer.service.userpersistence;

import com.dominik.typer.model.User;
import com.dominik.typer.service.adminpersistence.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserPersistence userPersistence;
    private final AdminService adminService;
    public void saveWithAdmin(String username, final User user) {
        if (adminService.checkIfEmpty()) {
            userPersistence.saveAdmin(user);
        } else if (adminService.isAdmin(username)) {
            userPersistence.saveWithAdmin(username, user);
        }
    }
    public void deleteUser(final Integer id) {
        userPersistence.deleteUserById(id);
    }

    public Optional<User> getUser(final Integer id) {
        return userPersistence.getUserById(id);
    }

    public List<User> getUsers() {
        return userPersistence.getAllUsers();
    }
    public void updateUser(Integer id, User user) {
        userPersistence.updateUserById(id, user);
    }
    public Optional<User> getUserWithUsername(String username) {
        return userPersistence.getUserByUsername(username);
    }
}

