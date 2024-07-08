package com.example.auth_app_new.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_app_new.Dto.ApiResponse;
import com.example.auth_app_new.Dto.RegisterUserDTo;
import com.example.auth_app_new.Service.AuthService;

import jakarta.validation.Valid;
@Controller
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterUserDTo dto){
        ApiResponse register = authService.register(dto);
        return ResponseEntity.status(register.getCode()).body(register);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam(name = "PhoneNumber")String PhoneNumber , @RequestParam String Code){
        ApiResponse register = authService.verifyUser(PhoneNumber,Code);
        return ResponseEntity.status(register.getCode()).body(register);
    }

    @PostMapping("/resendCode")
    public ResponseEntity<?> ResendCode(@RequestParam(name = "PhoneNumber")String PhoneNumber){
        ApiResponse register = authService.ResendCode(PhoneNumber);
        return ResponseEntity.status(register.getCode()).body(register);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam(name = "PhoneNumber")String PhoneNumber , @RequestParam String password){
        ApiResponse register = authService.login(PhoneNumber,password);
        return ResponseEntity.status(register.getCode()).body(register);
    }



}
