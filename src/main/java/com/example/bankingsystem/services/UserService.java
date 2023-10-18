package com.example.bankingsystem.services;

import com.example.bankingsystem.entities.User;
import com.example.bankingsystem.jwt.AuthResponse;
import com.example.bankingsystem.services.dto.UserInDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {
    public ResponseEntity<User> showById(Long id);
    public ResponseEntity<List<User>> showALl();
    public ResponseEntity<User> updateUser(Long userId,
                                           String name,
                                           String lastName,
                                           String dni,
                                           String email,
                                           LocalDate dateOfBirth);
    public ResponseEntity<?> deleteUser(Long id);
}
