package com.example.application.space.exercise.repository;

import com.example.application.space.exercise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Possible methods :
 * save(), findOne(), findById(), findAll(), count(), delete(), deleteById()
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}