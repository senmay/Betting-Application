package com.dominik.typer.service.adminPersistence;

import com.dominik.typer.enumerations.UserRole;
import com.dominik.typer.model.User;
import com.dominik.typer.model.entity.UserEntity;
import com.dominik.typer.model.exceptions.MyAppException;
import com.dominik.typer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    UserRepository userRepository;

    public void isAdmin(final User user){
        if (!user.getUserType().equals(UserRole.ADMIN)) {
            throw new MyAppException("User is not an admin: " + user.getUsername());
        }
    }
    public boolean isAdmin(final String username){
        UserEntity user = userRepository.getByUsername(username)
                .orElseThrow(() -> new MyAppException("User not found: " + username));
        if (!user.getUserRole().equals(UserRole.ADMIN)) {
            throw new MyAppException("User is not an admin: " + username);
        }
        return true;
    }


}
