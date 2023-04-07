package com.dominik.typer.service.userpersistence;

import com.dominik.typer.model.User;
import com.dominik.typer.service.adminpersistence.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserPersistence userPersistence;
    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;
    public void saveWithAdmin(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (adminService.checkIfEmpty()) {
            userPersistence.saveAdmin(user);
        } else {
            userPersistence.saveWithAdmin(user);
        }
    }
    public void register(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (adminService.checkIfEmpty()) {
            userPersistence.saveAdmin(user);
        } else {
            userPersistence.register(user);
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
    public void updateBalance(Integer id, Double betValue) {
        userPersistence.updateBalance(id, betValue);
    }
}

