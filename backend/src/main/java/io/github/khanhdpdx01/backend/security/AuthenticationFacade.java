package io.github.khanhdpdx01.backend.security;

import io.github.khanhdpdx01.backend.dto.user.UserPrinciple;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AuthenticationFacade {
    private Authentication authentication;

    public Long getUserId() {
        if (authentication == null) {
            authentication = SecurityContextHolder.getContext().getAuthentication();
        }

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return userPrinciple.getUserId();
    }
}