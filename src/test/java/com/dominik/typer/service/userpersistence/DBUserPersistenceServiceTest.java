package com.dominik.typer.service.userpersistence;

import com.dominik.typer.DataForTests.UserProvider;
import com.dominik.typer.enumerations.UserRole;
import com.dominik.typer.model.User;
import com.dominik.typer.model.entity.UserEntity;
import com.dominik.typer.model.mapper.UserMapper;
import com.dominik.typer.model.mapper.UserMapperImpl;
import com.dominik.typer.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DBUserPersistenceServiceTest implements UserProvider {
    @Spy
    UserMapper userMapper = new UserMapperImpl();
    @Mock
    UserRepository userRepository;
    @InjectMocks
    DBUserPersistenceService dbUserPersistenceService;


    @Test
    @DisplayName("Given user json when register first user then user is registered with role admin")
    void givenUserJson_whenSaveAdmin_thenUserIsRegisteredWithRoleAdmin() {
        //given
        User user = provideUser();
        user.setUserType(null);
        UserEntity userEntity = userMapper.mapToUserEntity(user);
        given(userRepository.save(userEntity)).willReturn(userEntity);
        //when
        dbUserPersistenceService.saveAdmin(user);
        //then
        assertThat(user.getUserType()).isEqualTo(UserRole.ADMIN);
    }

    @Test
    @DisplayName("Given user json when register second user then user is registered with role user")
    void givenUserJson_whenSaveWithAdmin_thenUserIsRegisteredWithRoleUser() {
        //given
        User user = provideUser();
        user.setUserType(null);
        UserEntity userEntity = userMapper.mapToUserEntity(user);
        given(userRepository.save(userEntity)).willReturn(userEntity);
        //when
        dbUserPersistenceService.saveUser(user);
        //then
        assertThat(user.getUserType()).isEqualTo(UserRole.USER);
    }
    @Test
    @DisplayName("Given String username get user by username")
    void givenUsername_whenGetUserByUsername_returnUser() {
        //given
        String username = "Dominik";
        User expectedUser = provideUser();
        UserEntity userEntity = userMapper.mapToUserEntity(expectedUser);
        given(userRepository.getByUsername(username)).willReturn(Optional.of(userEntity));
        //when
        Optional<User> actualUser = dbUserPersistenceService.getUserByUsername(username);
        //then
        assertThat(actualUser).isNotNull();
        User actualUser1 = actualUser.get();
        assertThat(actualUser1).isEqualTo(expectedUser);
    }
}