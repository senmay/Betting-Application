package com.dominik.typer.security;

import com.dominik.typer.model.User;
import com.dominik.typer.service.userpersistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserPersistence userPersistence;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userPersistence.getUserByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user.get());
    }
}
