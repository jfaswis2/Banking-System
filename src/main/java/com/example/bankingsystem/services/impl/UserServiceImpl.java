package com.example.bankingsystem.services.impl;

import com.example.bankingsystem.entities.User;
import com.example.bankingsystem.exception.ResourceConflictException;
import com.example.bankingsystem.exception.ResourceNotFoundException;
import com.example.bankingsystem.mapper.UserInDTOToUser;
import com.example.bankingsystem.payload.repositories.UserRepository;
import com.example.bankingsystem.services.UserService;
import com.example.bankingsystem.services.dto.UserInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserInDTOToUser userInDTOToUser;

    @Override
    public ResponseEntity<User> createUser(UserInDTO userInDTO) {

        if (userRepository.findByDni(userInDTO.getDni()).isPresent()) {
            throw new ResourceConflictException(String.format("El DNI: %s ya está registrado en el sistema", userInDTO.getDni()));
        }

        if (userRepository.findByEmail(userInDTO.getEmail()).isPresent()) {
            throw new ResourceConflictException(String.format("El email: %s ya está registrado en el sistema", userInDTO.getEmail()));
        }
        return ResponseEntity.ok(userRepository.save(userInDTOToUser.map(userInDTO)));
    }

    @Override
    @Transactional
    public ResponseEntity<User> updateUser(Long userId,
                                           String name,
                                           String lastName,
                                           String dni,
                                           String email,
                                           LocalDate dateOfBirth) {

        User user = userRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }

        if (lastName != null &&
                lastName.length() > 0 &&
                !Objects.equals(user.getLastName(), lastName)) {
            user.setLastName(lastName);
        }

        if (dni != null &&
                dni.length() > 0 &&
                !Objects.equals(user.getDni(), dni)) {
            Optional<User> userOptional = userRepository.findByDni(dni);
            if (userOptional.isPresent()) {
                throw new ResourceConflictException(String.format("El dni: %s ya está registrado en el sistema", dni));
            }
            user.setDni(dni);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(user.getEmail(), email)) {
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                throw new ResourceConflictException(String.format("El email: %s ya está registrado en el sistema", email));
            }
            user.setEmail(email);
        }

        if (dateOfBirth != null &&
                !dateOfBirth.isAfter(LocalDate.now()) &&
                !Objects.equals(user.getDateOfBirth(), dateOfBirth)) {
            user.setDateOfBirth(dateOfBirth);
        }
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        User user = userRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(()-> new ResourceNotFoundException("user", "id", id));

        user.setDeleted(true);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> showById(Long id) {
        User user = userRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<User>> showALl() {
        List<User> users = userRepository.findByDeletedFalse();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("users");
        }
        return ResponseEntity.ok(users);
    }
}
