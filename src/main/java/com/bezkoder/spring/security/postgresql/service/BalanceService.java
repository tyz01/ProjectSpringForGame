package com.bezkoder.spring.security.postgresql.service;

import com.bezkoder.spring.security.postgresql.models.Balance;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.repository.BalanceRepository;
import com.bezkoder.spring.security.postgresql.repository.UserRepository;
import com.bezkoder.spring.security.postgresql.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class BalanceService {
    private BalanceRepository balanceRepository;
    private UserRepository userRepository;

    public Balance createBalance(Balance balance) {
        return balanceRepository.save(balance);
    }

    public BigDecimal checkBalance(Long accountId) {
        User user = userRepository.findById(accountId).orElseThrow(NotFoundException::new);
        return user.getBalance().getAmountMoney();
    }

    public BigDecimal addMoneyOnBalance(Long accountId, BigDecimal moneyAdd) {
        Balance balance = balanceRepository.findBalanceById(accountId);
        final BigDecimal currentBalance = balance.getAmountMoney();
        if (currentBalance == null) {
            balance.setAmountMoney(moneyAdd);
            balanceRepository.save(balance);
            return moneyAdd;
        } else {
            final BigDecimal balanceFinal = currentBalance.add(moneyAdd);
            balance.setAmountMoney(balanceFinal);
            balanceRepository.save(balance);
            return balanceFinal;
        }
    }

    public BigDecimal findBalanceById(Long accountId) {
        Balance balance = balanceRepository.findBalanceById(accountId);
        return balance.getAmountMoney();
    }
}