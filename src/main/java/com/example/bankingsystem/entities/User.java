package com.example.bankingsystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private LocalDate dateOfBirth;
    @NotEmpty
    private LocalDate creationDate;
    @NotNull
    private boolean deleted;

    public User(Long id, String name, String lastName, String email, String dni, String password, LocalDate dateOfBirth, LocalDate creationDate, boolean deleted) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dni = dni;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.creationDate = creationDate;
        this.deleted = deleted;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
