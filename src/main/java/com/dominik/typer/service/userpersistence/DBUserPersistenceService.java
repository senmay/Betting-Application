package com.dominik.typer.service.userpersistence;

import com.dominik.typer.enumerations.UserRole;
import com.dominik.typer.model.User;
import com.dominik.typer.model.entity.UserEntity;
import com.dominik.typer.model.mapper.UserMapper;
import com.dominik.typer.repository.UserRepository;
import com.dominik.typer.service.adminpersistence.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile({"db", "!cache"})
public class DBUserPersistenceService implements UserPersistence {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final AdminService adminService;


    @Autowired
    public DBUserPersistenceService(UserMapper userMapper, UserRepository userRepository, AdminService adminService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.adminService = adminService;
    }

    @Override
    public void saveAdmin(User user) {
        user.setUserType(UserRole.ADMIN);
        userRepository.save(userMapper.mapToUserEntity(user));
    }

    @Override
    public void saveWithAdmin(String username, User user) {
        user.setUserType(UserRole.USER);
        userRepository.save(userMapper.mapToUserEntity(user));
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.mapToListUser(userRepository.findAll());
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.map(userMapper::mapToUser);

    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        Optional<UserEntity> userEntity = userRepository.getByUsername(username);
        return userEntity.map(userMapper::mapToUser);
    }

    @Override
    public void deleteUserById(Integer id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new RuntimeException("No user with id " + id);
        }
    }

    @Override
    public void updateUserById(Integer id, User user) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("No user with id " + id);
        }
        Optional<UserEntity> userEntity = userRepository.findById(id);
        User updatedUser = userMapper.mapToUser(userEntity.get());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPoints(user.getPoints());
        userRepository.save(userMapper.mapToUserEntity(user));
    }
}
