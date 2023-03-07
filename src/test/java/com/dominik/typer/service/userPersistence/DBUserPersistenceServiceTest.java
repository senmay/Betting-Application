package com.dominik.typer.service.userPersistence;

import com.dominik.typer.enumerations.UserRole;
import com.dominik.typer.model.User;
import com.dominik.typer.model.entity.UserEntity;
import com.dominik.typer.model.mapper.UserMapper;
import com.dominik.typer.repository.UserRepository;
import com.dominik.typer.service.adminPersistence.AdminService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DBUserPersistenceServiceTest {
    @Mock
    UserService userService;
    @Spy
    UserMapper userMapper;
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
                .points(0)
                .email("dom@gmail.com")
                .build();
        UserEntity userEntity = userMapper.mapToUserEntity(user);
        given(userRepository.existsById(1)).willReturn(false);
        given(userRepository.save(userEntity)).willReturn(userEntity);
        //when
        dbUserPersistenceService.save(user);
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
                .points(0)
                .email("dom@gmail.com")
                .build();
        UserEntity userEntity = userMapper.mapToUserEntity(user);
        given(userRepository.existsById(2)).willReturn(false);
        given(userRepository.save(userEntity)).willReturn(userEntity);
        //when
        dbUserPersistenceService.save(user);
        //then
        assertThat(user.getUserType()).isEqualTo(UserRole.USER);
    }

    @Test
    @DisplayName("Given user json when register user with existing id then throw exception")
    void givenUserJson_whenRegisterUserWithExistingId_thenThrowException() {
        //given
        User user = User.builder()
                .id(1)
                .username("Dominik")
                .points(0)
                .email("dom@Gmail.com")
                .build();
        given(userRepository.existsById(1)).willReturn(true);
        //then
        assertThatThrownBy(() -> dbUserPersistenceService.save(user))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("User with id " + user.getId() + " already exists");

    }

    @Test
    @DisplayName("Given String username get user by username")
    void givenUsername_whenGetUserByUsername_returnUser() {
        //given
        String username = "Dominik";
        User expectedUser = User.builder()
                .id(1)
                .username("Dominik")
                .points(0)
                .email("dom@wp.pl")
                .build();
        UserEntity userEntity = userMapper.mapToUserEntity(expectedUser);
        given(userRepository.getByUsername(username)).willReturn(Optional.of(userEntity));
        //when
        User actualUser = dbUserPersistenceService.getUserByUsername(username);
        //then
        assertThat(actualUser)
                .isNotNull()
                .isEqualTo(expectedUser);
    }
    @Test
    @DisplayName("If user is not an admin don't save user")
    void givenUser_whenSaveWithAdmin_returnNothing() {
        //given
        User user = User.builder()
                .id(1)
                .username("Dominik")
                .points(0)
                .email("dom@wp.pl")
                .userType(UserRole.ADMIN)
                .build();
        UserEntity userEntity = userMapper.mapToUserEntity(user);
        given(adminService.isAdmin("Dominik")).willReturn(false);
        //when
        dbUserPersistenceService.saveWithAdmin("Dominik", user);
        //then
        then(userRepository).shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("If user is an admin save user")
    void givenAdmin_whenSaveWithAdmin_throwException() {
        //given
        User user = User.builder()
                .id(1)
                .username("Dominik")
                .points(0)
                .email("dom@wp.pl")
                .userType(UserRole.USER)
                .build();
        given(adminService.isAdmin("Dominik")).willReturn(true);
        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);
        //when
        dbUserPersistenceService.saveWithAdmin("Dominik", user);
        then(userRepository).should().save(captor.capture());
    }
}