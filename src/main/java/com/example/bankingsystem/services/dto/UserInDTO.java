package com.example.bankingsystem.services.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInDTO {
    private String name;
    private String lastName;
    private String email;
    private String dni;
    private String password;
    private LocalDate dateOfBirth;
}
