package com.dominik.typer.controller;

import com.dominik.typer.model.User;
import com.dominik.typer.model.exceptions.DbError;
import com.dominik.typer.model.json.UserJson;
import com.dominik.typer.model.mapper.UserMapper;
import com.dominik.typer.service.DbErrorService;
import com.dominik.typer.service.userpersistence.UserService;
import com.dominik.typer.validators.GeneralValidator;
import com.dominik.typer.validators.ValidationGroupJson;
import com.dominik.typer.validators.ValidationGroupPutJson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserJsonController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final DbErrorService dbErrorService;
    private final GeneralValidator validator;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<UserJson> getAllUsers() {
        List<User> userList = userService.getUsers();
        return userMapper.mapToUserJsonList(userList);
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    void saveUser(@RequestHeader("login") String username, @RequestBody UserJson userJson) {
        validator.validateObject(userJson, ValidationGroupJson.class);
        userService.saveUser(username, userMapper.mapFromJson(userJson));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Optional<UserJson> getUser(@PathVariable Integer id) {
        Optional<User> user = userService.getUser(id);
        return user.map(userMapper::mapToJson);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void updateUser(@PathVariable Integer id, @RequestBody UserJson updatedUser) {
        validator.validateObject(updatedUser, ValidationGroupPutJson.class);
        userService.updateUser(id, userMapper.mapFromJson(updatedUser));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteUser(@PathVariable Integer id) {
        validator.validatePathVariable(id);
        userService.deleteUser(id);
    }

    @PostMapping("/balance")
    @ResponseStatus(HttpStatus.CREATED)
    void updateBalance(@RequestBody UserJson userJson) {
        userService.updateBalance(userJson.getId(), userJson.getBalance());
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
