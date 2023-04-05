package com.dominik.typer.security;

import com.dominik.typer.model.exceptions.MyAppException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {
    public void checkIfAuthenticatedUserHasPermissionToSeeUser(Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserPrincipal userDetails = (MyUserPrincipal) authentication.getPrincipal();
        Integer authenticatedUserId = userDetails.getId();
        if (!authenticatedUserId.equals(id)) {
            throw new MyAppException("You don't have permission to see this user");
        }
    }
}
