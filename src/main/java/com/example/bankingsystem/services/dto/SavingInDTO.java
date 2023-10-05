package com.example.bankingsystem.services.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SavingInDTO {
    @DecimalMax(value = "0.5", message = "La tasa de interés no debe ser superior al 0.5")
    private BigDecimal interestRate;
    @Min(value = 100, message = "El saldo no debe ser menor a 100")
    private BigDecimal balance;
}
