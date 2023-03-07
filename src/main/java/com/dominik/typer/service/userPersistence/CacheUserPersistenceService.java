package com.dominik.typer.service.userPersistence;

import com.dominik.typer.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

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
    public void save(User user) {
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
    public User getUserById(Integer id) {
        if (!data.containsKey(id)) {
            throw new RuntimeException("No user with id " + id);
        }
        return data.get(id);
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
    public User getUserByUsername(String username) {
        return null;
    }

}
