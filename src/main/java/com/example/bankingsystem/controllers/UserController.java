package com.example.bankingsystem.controllers;

import com.example.bankingsystem.services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping("/user")
    public ResponseEntity<?> showUser(HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        return userService.showUser(email);
    }

    @GetMapping("/users")
    public ResponseEntity<?> showAll() {
        return userService.showALl();
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String token) {
        return userService.deleteUser(token);
    }
}
