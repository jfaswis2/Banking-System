package com.example.bankingsystem.repositories;

import com.example.bankingsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByDni(String dni);
    Optional<User> findByEmail(String email);
}
