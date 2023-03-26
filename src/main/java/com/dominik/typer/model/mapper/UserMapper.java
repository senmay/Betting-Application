package com.dominik.typer.model.mapper;

import com.dominik.typer.model.User;
import com.dominik.typer.model.entity.UserEntity;
import com.dominik.typer.model.json.UserJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper
public interface UserMapper {
    @Mapping(target="points", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, defaultValue = "0")
    @Mapping(target="balance", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, defaultValue = "0.00")
    @Mapping(target="userType", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, defaultValue = "USER")
    User mapFromJson(UserJson userJson);
    List<UserEntity> mapToListUserEntity(List<User> users);
    List<User> mapToListUser(List<UserEntity> usersEntity);
    User mapToUser(UserEntity userEntity);
    UserEntity mapToUserEntity(User user);
    UserJson mapToJson(User user);
    List<User> mapFromListJson(List<UserJson> userJsonList);
    List<UserJson> mapToUserJsonList(List<User> userList);
}
