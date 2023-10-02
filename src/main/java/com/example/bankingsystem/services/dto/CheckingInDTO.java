package com.example.bankingsystem.services.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CheckingInDTO {
    @Min(value = 250, message = "Las cuentas corrientes deben de tener un saldo mínimo de 250")
    private BigDecimal minimumBalance;
    @Min(value = 12, message = "Las cuentas corrientes deben de tener una cuota mensual de mantenimiento de 12")
    private BigDecimal monthlyMaintenanceFee;
}
