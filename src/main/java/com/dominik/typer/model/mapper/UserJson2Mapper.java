package com.dominik.typer.model.mapper;

import com.dominik.typer.model.User;
import com.dominik.typer.model.json.UserJson2;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserJson2Mapper {
    @Mapping(target = "fullName", source = "username")
    @Mapping(target = "totalPoints", source = "points")
    @Mapping(target = "mailingAdress", source = "email")
    UserJson2 map(User user);
    @InheritInverseConfiguration
    User mapFromJson(UserJson2 userJson);
    List<User> mapFromListJson(List<UserJson2> userJsonList);
    List<UserJson2> mapToList (List<User> userList);
}
