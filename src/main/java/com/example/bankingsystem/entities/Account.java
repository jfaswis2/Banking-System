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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
