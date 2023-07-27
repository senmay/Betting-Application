package com.dominik.typer.service.userpersistence;

import com.dominik.typer.DataForTests.UserProvider;
import com.dominik.typer.model.User;
import com.dominik.typer.service.adminpersistence.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserServiceTest implements UserProvider {
    @Mock
    UserPersistence userPersistence;
    @Mock
    AdminService adminService;
    @InjectMocks
    UserService userService;

    @Test
    void givenUserDatabaseEmpty_whenSaveWithAdmin_thenSaveAdminWithCorrectMethod(){
        //given
        User user = provideUser();
        String username = "something";
        given(adminService.checkIfEmpty()).willReturn(true);
        //when
        userService.saveUser(username, user);
        //then
        then(userPersistence).should().saveAdmin(user);
        then(userPersistence).shouldHaveNoMoreInteractions();
    }

    @Test
    void givenUserDatabaseHasAdminUser_whenSaveWithAdmin_thenSaveWithAdminWithCorrectMethod(){
        //given
        User user = provideUser();
        String username = "something";
        given(adminService.checkIfEmpty()).willReturn(false);
        given(adminService.isAdmin(username)).willReturn(true);
        //when
        userService.saveUser(username, user);
        //then
        then(userPersistence).should().saveUser(user);
        then(userPersistence).shouldHaveNoMoreInteractions();
    }
}