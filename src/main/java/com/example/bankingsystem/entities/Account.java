package com.example.bankingsystem.entities;

import com.example.bankingsystem.enums.Status;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@Builder
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal balance;
    private BigDecimal penaltyFee;
    private BigDecimal minimumBalance;
    private LocalDate creationDate;
    private Status status;
    private boolean deleted;

    public Account() {
    }

    public Account(BigDecimal balance, BigDecimal penaltyFee, BigDecimal minimumBalance, LocalDate creationDate, Status status, boolean deleted) {
        this.balance = balance;
        this.penaltyFee = penaltyFee;
        this.minimumBalance = minimumBalance;
        this.creationDate = creationDate;
        this.status = status;
        this.deleted = deleted;
    }
}
