package com.example.bankingsystem.services;

import com.example.bankingsystem.entities.User;
import com.example.bankingsystem.services.dto.UserInDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public ResponseEntity<User> createUser(UserInDTO userInDTO);
    public ResponseEntity<User> showById(Long id);
    public ResponseEntity<List<User>> showALl();
}
