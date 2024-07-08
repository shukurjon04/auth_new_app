package com.example.auth_app_new.Entity.Auditing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.auth_app_new.Entity.UserForNewsApp;

@Configuration
@EnableJpaAuditing
public class AuditingConfiguration {

    @Bean
    public AuditorAware<UserForNewsApp> auditorAware(){
        return new AuditingSecurity();
    }
}
