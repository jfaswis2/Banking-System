package com.example.bankingsystem.services.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInDTO {
    @NotEmpty(message = "El nombre no debe ser nulo ni estar vacío.")
    private String name;
    @NotEmpty(message = "El apellido no debe ser nulo ni estar vacío.")
    private String lastName;
    @NotEmpty(message = "El email no debe ser nulo ni estar vacío.")
    @Email(
            regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
            message = "No es un email válido")
    private String email;
    @Pattern(regexp = "^\\d{8}[A-HJ-NP-TV-Z]$", message = "No es un DNI válido")
    @NotEmpty(message = "El DNI no debe ser nulo ni estar vacío.")
    private String dni;
    @NotEmpty(message = "La contraseña no debe ser nula ni estar vacía.")
    private String password;
    @NotNull(message = "La fecha de nacimiento no debe ser nula")
    @Past(message = "La fecha de nacimiento debe de ser una fecha del pasado")
    private LocalDate dateOfBirth;
}
