package com.example.bankingsystem.entities;

import com.example.bankingsystem.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Checking extends Account{
    private BigDecimal monthlyMaintenanceFee;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Checking() {
    }

    public Checking(BigDecimal balance, BigDecimal penaltyFee, BigDecimal minimumBalance, LocalDate creationDate, Status status, boolean deleted, BigDecimal monthlyMaintenanceFee, User user) {
        super(balance, penaltyFee, minimumBalance, creationDate, status, deleted);
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.user = user;
    }
}
