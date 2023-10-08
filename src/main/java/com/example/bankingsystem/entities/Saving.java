package com.example.bankingsystem.entities;

import com.example.bankingsystem.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Saving extends Account{
    private BigDecimal interestRate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Saving() {
    }

    public Saving(BigDecimal balance, BigDecimal penaltyFee, BigDecimal minimumBalance, LocalDate creationDate, Status status, boolean deleted, BigDecimal interestRate, User user) {
        super(balance, penaltyFee, minimumBalance, creationDate, status, deleted);
        this.interestRate = interestRate;
        this.user = user;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
