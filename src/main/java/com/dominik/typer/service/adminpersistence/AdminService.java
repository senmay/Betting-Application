package com.dominik.typer.service.adminpersistence;

import com.dominik.typer.enumerations.UserRole;
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
    private final UserRepository userRepository;

    public boolean isAdmin(final String username){
        UserEntity user = userRepository.getByUsername(username)
                .orElseThrow(() -> new MyAppException("User not found: " + username));
        if (!user.getUserType().equals(UserRole.ADMIN)) {
            throw new MyAppException("User is not an admin: " + username);
        }
        return true;
    }
    // if database is empty then first user is admin
    public boolean checkIfEmpty() {
        return userRepository.findAll().isEmpty();
    }
}
