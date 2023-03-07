package com.dominik.typer.model.mapper;

import DataForTests.ProvideUser;
import com.dominik.typer.model.User;
import com.dominik.typer.model.json.UserJson;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest implements ProvideUser {
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
                .isEqualTo(UserJson.builder()
                        .id(1)
                        .username("Dominik")
                        .points(5)
                        .email("admin@wp.pl")
                        .build());
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
                .isEqualTo(User.builder()
                        .id(2)
                        .username("Marcin")
                        .points(3)
                        .email("user@gmail.com")
                        .userType(null)
                        .balance(null)
                        .build());
    }

    @Test
    void givenListOfUsers4Elements_whenMapToUserJsonList_returnUserJsonList() {
        //given
        List<User> userList = provideUserListOf4Elements();
        //when
        userJsonMapper.mapToUserJsonList(userList);
        //then
        assertThat(userJsonMapper.mapToUserJsonList(userList))
                .isNotNull()
                .hasSize(4)
                .contains(UserJson.builder().id(1).username("Dominik").points(5).email("admin@wp.pl").build()
                        , UserJson.builder().id(4).username("Krzysztof").points(1).email("user2@gmail.com").build());

    }
}

