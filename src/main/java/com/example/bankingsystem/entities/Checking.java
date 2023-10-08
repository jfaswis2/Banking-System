package com.example.bankingsystem.entities;

import com.example.bankingsystem.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Checking extends Account{
    private BigDecimal monthlyMaintenanceFee;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Checking() {
    }

    public Checking(BigDecimal balance,
                    BigDecimal penaltyFee,
                    BigDecimal minimumBalance,
                    LocalDate creationDate,
                    Status status,
                    boolean deleted,
                    BigDecimal monthlyMaintenanceFee,
                    User user) {
        super(balance, penaltyFee, minimumBalance, creationDate, status, deleted);
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.user = user;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
