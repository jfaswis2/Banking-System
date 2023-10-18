package com.example.bankingsystem.controllers;

import com.example.bankingsystem.jwt.AuthResponse;
import com.example.bankingsystem.services.dto.UserInDTO;
import com.example.bankingsystem.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class UserController {

    private final UserServiceImpl userService;

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) String lastName,
                                           @RequestParam(required = false) String dni,
                                           @RequestParam(required = false) String email,
                                           @RequestParam(required = false) LocalDate dateOfBirth) {
        return userService.updateUser(userId, name, lastName, dni, email, dateOfBirth);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        return userService.showById(id);
    }

    @GetMapping("/users")
    public ResponseEntity<?> showAll() {
        return userService.showALl();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
