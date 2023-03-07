package com.dominik.typer.service.adminPersistence;

import com.dominik.typer.enumerations.UserRole;
import com.dominik.typer.model.User;
import com.dominik.typer.model.entity.UserEntity;
import com.dominik.typer.model.exceptions.MyAppException;
import com.dominik.typer.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {
    @InjectMocks
    AdminService adminService;
    @Mock
    UserRepository userRepository;
    @Test
    public void givenAdminUser_whenIsAdmin_thenReturnTrue() {
        //given
        UserEntity userEntity = UserEntity.builder().username("Dominik").userRole(UserRole.ADMIN).build();
        given(userRepository.getByUsername("Dominik")).willReturn(Optional.ofNullable(userEntity));
        //when
        boolean result = adminService.isAdmin("Dominik");
        //then
        assertTrue(result);
    }
    @Test
    public void givenNonAdminUser_whenIsAdmin_thenThrowException() {
        //given
        UserEntity userEntity = UserEntity.builder().username("Dominik").userRole(UserRole.USER).build();
        given(userRepository.getByUsername("Dominik")).willReturn(Optional.ofNullable(userEntity));
        //when
        assertThatThrownBy(() -> adminService.isAdmin("Dominik"))
                .isInstanceOf(MyAppException.class)
                .hasMessage("User is not an admin: Dominik");
    }

}