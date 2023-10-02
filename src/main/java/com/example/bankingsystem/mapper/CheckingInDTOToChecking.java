package com.example.bankingsystem.mapper;

import com.example.bankingsystem.entities.Checking;
import com.example.bankingsystem.enums.Status;
import com.example.bankingsystem.services.dto.CheckingInDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CheckingInDTOToChecking implements IMapper<CheckingInDTO, Checking>{
    @Override
    public Checking map(CheckingInDTO in) {
        Checking checking = new Checking();
        checking.setMinimumBalance(in.getMinimumBalance());
        checking.setMonthlyMaintenanceFee(in.getMonthlyMaintenanceFee());
        checking.setBalance(BigDecimal.valueOf(0L));
        checking.setDeleted(false);
        checking.setCreationDate(LocalDate.now());
        checking.setStatus(Status.ACTIVE);
        checking.setPenaltyFee(BigDecimal.valueOf(40L));
        return checking;
    }
}
