package com.example.auth_app_new.Entity.Auditing;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.auth_app_new.Entity.UserForNewsApp;

public class AuditingSecurity implements AuditorAware<UserForNewsApp> {

    @Override
    public Optional<UserForNewsApp> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")){
            return Optional.of((UserForNewsApp)authentication.getPrincipal());
        }
        return Optional.empty();
        
    }

}
