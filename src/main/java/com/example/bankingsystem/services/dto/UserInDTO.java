package com.example.bankingsystem.services.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInDTO {
    @NotEmpty(message = "El nombre no debe ser nulo ni estar vacío.")
    private String name;
    @NotEmpty(message = "El apellido no debe ser nulo ni estar vacío.")
    private String lastName;
    @NotEmpty(message = "El apellido no debe ser nulo ni estar vacío.")
    @Email(
            regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
            message = "No es un email válido")
    private String email;
    @Pattern(regexp = "^(0[1-9]|[1-9][0-9])\\\\d{7}[A-Za-z]$", message = "No es un DNI válido")
    @NotEmpty(message = "El DNI no debe ser nulo ni estar vacío.")
    private String dni;
    @NotEmpty(message = "La contraseña no debe ser nula ni estar vacía.")
    private String password;
    @NotEmpty(message = "La fecha de nacimiento no debe ser nula ni estar vacía.")
    @Past(message = "La fecha de nacimiento debe de ser una fecha del pasado")
    private LocalDate dateOfBirth;
}
