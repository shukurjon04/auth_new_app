package com.example.auth_app_new.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth_app_new.Entity.UserForNewsApp;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserForNewsApp, Long> {
    boolean existsByPhonenumber(String phonenumber);
    Optional<UserForNewsApp>  findByPhonenumber(String phonenumber);
}
