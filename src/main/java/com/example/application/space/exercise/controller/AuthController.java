package com.example.application.space.exercise.controller;

import com.example.application.space.exercise.model.User;
import com.example.application.space.exercise.model.form.LoginForm;
import com.example.application.space.exercise.repository.UserRepository;
import com.example.application.space.exercise.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String login(@RequestBody LoginForm form){
        return authService.login(form);
    }

}
