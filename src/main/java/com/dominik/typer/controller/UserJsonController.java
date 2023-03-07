package com.dominik.typer.controller;

import com.dominik.typer.model.User;
import com.dominik.typer.model.exceptions.DbError;
import com.dominik.typer.model.json.UserJson;
import com.dominik.typer.model.mapper.UserMapper;
import com.dominik.typer.service.DbErrorService;
import com.dominik.typer.service.userPersistence.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserJsonController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final DbErrorService dbErrorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void registerUser(@RequestBody @Validated UserJson userJson) {
        userService.saveUser(userMapper.mapFromJson(userJson));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<UserJson> getAllUsers() {
        List<User> userList = userService.getUsers();
        return userMapper.mapToUserJsonList(userList);
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    void registerUserWithAdmin(@RequestHeader("login") String username, @RequestBody UserJson userJson) {
        userService.saveWithAdmin(username, userMapper.mapFromJson(userJson));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserJson getUser(@PathVariable Integer id) {
        User userJson = userService.getUser(id);
        return userMapper.mapToJson(userJson);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void updateUser(@PathVariable Integer id, @RequestBody @Validated UserJson updatedUser) {
        userService.updateUser(id, userMapper.mapFromJson(updatedUser));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DbError handleRuntimeExceptionAsync(Exception exception) {
        DbError dbError = DbError.builder()
                .errorType(exception.getClass().getSimpleName())
                .errorMessage(exception.getMessage())
                .timestamp(LocalDateTime.now()).build();
        log.error("Before Async save!");
        dbErrorService.saveAsync(dbError);

        return dbError;
    }
}
