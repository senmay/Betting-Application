package com.dominik.typer.service.userpersistence;

import com.dominik.typer.DataForTests.UserProvider;
import com.dominik.typer.enumerations.UserRole;
import com.dominik.typer.model.User;
import com.dominik.typer.model.entity.UserEntity;
import com.dominik.typer.model.mapper.UserMapper;
import com.dominik.typer.model.mapper.UserMapperImpl;
import com.dominik.typer.repository.UserRepository;
import com.dominik.typer.service.adminpersistence.AdminService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DBUserPersistenceServiceTest implements UserProvider {
    @Mock
    UserService userService;
    @Spy
    UserMapper userMapper = new UserMapperImpl();
    @Mock
    UserRepository userRepository;
    @Mock
    AdminService adminService;
    @InjectMocks
    DBUserPersistenceService dbUserPersistenceService;


    @Test
    @DisplayName("Given user json when register first user then user is registered with role admin")
    void givenUserJson_whenRegisterFirstUser_thenUserIsRegisteredWithRoleAdmin() {
        //given
        User user = User.builder()
                .id(1)
                .username("Dominik")
                .password("123")
                .points(0)
                .email("dom@gmail.com")
                .build();
        UserEntity userEntity = userMapper.mapToUserEntity(user);
        given(userRepository.save(userEntity)).willReturn(userEntity);
        //when
        dbUserPersistenceService.saveAdmin(user);
        //then
        assertThat(user.getUserType()).isEqualTo(UserRole.ADMIN);
    }

    @Test
    @DisplayName("Given user json when register second user then user is registered with role user")
    void givenUserJson_whenRegisterSecondUser_thenUserIsRegisteredWithRoleUser() {
        //given
        User user = User.builder()
                .id(2)
                .username("Dominik")
                .password("password")
                .points(0)
                .email("dom@gmail.com")
                .build();
        UserEntity userEntity = userMapper.mapToUserEntity(user);
        //when
        dbUserPersistenceService.saveWithAdmin(user);
        //then
        assertThat(user.getUserType()).isEqualTo(UserRole.USER);
    }
}