package com.dominik.typer.model.mapper;

import com.dominik.typer.DataForTests.UserProvider;
import com.dominik.typer.model.User;
import com.dominik.typer.model.entity.UserEntity;
import com.dominik.typer.model.json.UserJson;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TestUserMapperProvider implements UserProvider {
    UserMapper userMapper = new UserMapperImpl();

    @Test
    void givenUser_whenMapToJson_returnUserJson() {
        //given
        User user = provideUser();
        //when
        userMapper.mapToJson(user);
        //then
        assertThat(userMapper.mapToJson(user))
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void givenUserJson_whenMapToUser_returnUser() {
        //given
        UserJson userJson = provideUserJson();
        //when
        userMapper.mapFromJson(userJson);
        //then
        assertThat(userMapper.mapFromJson(userJson))
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void givenListOfUsers4Elements_whenMapToUserJsonList_returnUserJsonList() {
        //given
        List<User> userList = provideUserListOfElements(4);
        //when
        userMapper.mapToUserJsonList(userList);
        //then
        assertThat(userMapper.mapToUserJsonList(userList))
                .isNotNull()
                .hasSize(4);
    }
    @Test
    void givenUser_whenMapToUserEntity_returnUserEntity() {
        //given
        User user = provideUser();
        //when
        userMapper.mapToUserEntity(user);
        UserEntity userEntity = userMapper.mapToUserEntity(user);
        System.out.println(user);
        System.out.println(userEntity);
        //then
        assertThat(userMapper.mapToUserEntity(user))
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }
}

