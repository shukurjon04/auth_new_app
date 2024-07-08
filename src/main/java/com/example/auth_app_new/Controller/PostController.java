package com.example.auth_app_new.Controller;

import com.example.auth_app_new.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PostController {
    @Autowired
    PostRepository postRepository;

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(postRepository.findAll());
    }
}
