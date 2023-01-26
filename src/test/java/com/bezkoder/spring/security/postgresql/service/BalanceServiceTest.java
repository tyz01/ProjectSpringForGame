package com.bezkoder.spring.security.postgresql.service;

import com.bezkoder.spring.security.postgresql.models.Balance;
import com.bezkoder.spring.security.postgresql.repository.BalanceRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@RunWith(SpringRunner.class)
class BalanceServiceTest {
    @Autowired
    private BalanceService balanceService;
    @MockBean
    private BalanceRepository balanceRepository;

    @Test
    void createBalance() {
        Balance balance = new Balance();
        balanceService.createBalance(balance);
        BigDecimal bigDecimal = new BigDecimal(10);
        balance.setAmountMoney(bigDecimal);
        balance.setId(1L);
        Assert.assertNotNull(balance);
        Assert.assertNotNull(balance.getId());
    }

    @Test
    void checkBalance() {

    }

    @Test
    void addMoneyOnBalance() {
    }

    @Test
    void findBalanceById() {
    }
}