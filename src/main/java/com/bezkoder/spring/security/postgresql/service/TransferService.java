package com.bezkoder.spring.security.postgresql.service;

import com.bezkoder.spring.security.postgresql.models.Balance;
import com.bezkoder.spring.security.postgresql.models.Transfer;
import com.bezkoder.spring.security.postgresql.repository.BalanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class TransferService {

    private BalanceRepository balanceRepository;

    public void transferBalance(Transfer transferBalance) {

        Balance balanceTo = balanceRepository.findBalanceById(transferBalance.getToBalance());
        Balance balanceFrom = balanceRepository.findBalanceById(transferBalance.getFromBalance());

        if (balanceFrom == null || balanceTo == null) {
            throw new IllegalArgumentException("no id");
        }

        if (transferBalance.getTransferMoney().compareTo(balanceFrom.getAmountMoney()) > 0) {
            throw new IllegalArgumentException("not enough money");
        }

        BigDecimal MoneyFromTransfer = balanceFrom.getAmountMoney();
        BigDecimal MoneyToTransfer = balanceTo.getAmountMoney();

        balanceTo.setAmountMoney(MoneyToTransfer.add(transferBalance.getTransferMoney()));
        balanceFrom.setAmountMoney(MoneyFromTransfer.subtract(transferBalance.getTransferMoney()));

        balanceRepository.save(balanceFrom);
        balanceRepository.save(balanceTo);

    }
}