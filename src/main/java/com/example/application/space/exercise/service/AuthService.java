package com.example.application.space.exercise.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.application.space.exercise.model.form.LoginForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {


    private final AuthenticationManager authManager;

    public AuthService(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    public String login(LoginForm form){

        Authentication authentication = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword());

        authManager.authenticate( authentication );

        return JWT.create()
                .withSubject(form.getEmail())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date( System.currentTimeMillis() + 86_400_000))
                .withClaim("roles", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .sign(Algorithm.HMAC512("m0N_Sup3R_C0d3-S3creT"));
    }

}
