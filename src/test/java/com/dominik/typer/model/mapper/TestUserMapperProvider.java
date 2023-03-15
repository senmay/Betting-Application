package com.dominik.typer.model.mapper;

import DataForTests.UserProvider;
import com.dominik.typer.model.User;
import com.dominik.typer.model.json.UserJson;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TestUserMapperProvider implements UserProvider {
    UserMapper userJsonMapper = new UserMapperImpl();

    @Test
    void givenUser_whenMapToJson_returnUserJson() {
        //given
        User user = provideUser();
        //when
        userJsonMapper.mapToJson(user);
        //then
        assertThat(userJsonMapper.mapToJson(user))
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void givenUserJson_whenMapToUser_returnUser() {
        //given
        UserJson userJson = provideUserJson();
        //when
        userJsonMapper.mapFromJson(userJson);
        //then
        assertThat(userJsonMapper.mapFromJson(userJson))
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void givenListOfUsers4Elements_whenMapToUserJsonList_returnUserJsonList() {
        //given
        List<User> userList = provideUserListOfElements(4);
        //when
        userJsonMapper.mapToUserJsonList(userList);
        //then
        assertThat(userJsonMapper.mapToUserJsonList(userList))
                .isNotNull()
                .hasSize(4);
    }
}

