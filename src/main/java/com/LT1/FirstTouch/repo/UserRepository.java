package com.LT1.FirstTouch.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LT1.FirstTouch.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}