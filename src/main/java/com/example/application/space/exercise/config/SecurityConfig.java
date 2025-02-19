package com.example.application.space.exercise.config;

import com.example.application.space.exercise.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        jsr250Enabled = true,
        securedEnabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder encoder;
    private final JwtProperties jwtProperties;

    public SecurityConfig(PasswordEncoder encoder, JwtProperties jwtProperties) {
        this.encoder = encoder;
        this.jwtProperties = jwtProperties;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(encoder.encode("pass")).roles("USER")
                .and()
                .withUser("admin").password(encoder.encode("pass")).roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Desactivation du cross Site Request Forgery
        http.csrf().disable();

        // Definition de securité pour les points d'entrées
        http.authorizeRequests().anyRequest().permitAll();

        //Gestion des filtres
        http.addFilterBefore(
                new JwtAuthenticationFilter(jwtProperties),
                UsernamePasswordAuthenticationFilter.class
        );

        // Assurer l'aspect Stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //h2
        http.headers().frameOptions().disable();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
