package com.dominik.typer.model.mapper;

import com.dominik.typer.model.User;
import com.dominik.typer.model.entity.UserEntity;
import com.dominik.typer.model.json.UserJson;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserEntity> mapToListUserEntity(List<User> users);
    List<User> mapToListUser(List<UserEntity> usersEntity);

    default User mapToUser(UserEntity userEntity){
        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .points(userEntity.getPoints())
                .userType(userEntity.getUserRole())
                .build();
    }
    default UserEntity mapToUserEntity(User user){
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .points(user.getPoints())
                .userRole(user.getUserType())
                .build();
    }
    UserJson mapToJson(User user);
    User mapFromJson(UserJson userJson);
    List<User> mapFromListJson(List<UserJson> userJsonList);
    List<UserJson> mapToUserJsonList(List<User> userList);



}
