package com.dominik.typer.service.userpersistence;

import com.dominik.typer.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Profile("cache")
@RequiredArgsConstructor
public class CacheUserPersistenceService implements UserPersistence {
    private final Map<Integer, User> data = new HashMap<>();

    @Override
    public void saveAdmin(User user) {
        if (data.containsKey(user.getId())) {
            throw new RuntimeException("User with id " + user.getId() + " already exists");
        }
        data.put(user.getId(), user);
    }

    //TODO  implement
    @Override
    public void saveWithAdmin(String username, User user) {

    }

    @Override
    public List<User> getAllUsers() {
        return data.values().stream().toList();
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        if (!data.containsKey(id)) {
            throw new RuntimeException("No user with id " + id);
        }
        return Optional.of(data.get(id));
    }

    @Override
    public void deleteUserById(Integer id) {
        if (!data.containsKey(id)) {
            throw new RuntimeException("No user with id " + id);
        }
        data.remove(id);
    }

    @Override
    public void updateUserById(Integer id, User user) {
        if (!data.containsKey(id)) {
            throw new RuntimeException("No user with id " + id);
        }
        data.put(id, user);
    }

    //TODO  implement
    @Override
    public Optional<User> getUserByUsername(String username) {
        return null;
    }

    @Override
    public void updateBalance(Integer id, BigDecimal balance) {
        if (!data.containsKey(id)) {
            throw new RuntimeException("No user with id " + id);
        }
        User user = data.get(id);
        user.setBalance(user.getBalance().add(balance));
        data.put(id, user);
    }

}
