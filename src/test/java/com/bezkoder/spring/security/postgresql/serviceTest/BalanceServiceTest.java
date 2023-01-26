package com.bezkoder.spring.security.postgresql.serviceTest;


import com.bezkoder.spring.security.postgresql.models.Balance;
import com.bezkoder.spring.security.postgresql.repository.BalanceRepository;
import com.bezkoder.spring.security.postgresql.service.BalanceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

@SpringBootTest
public class BalanceServiceTest {
    @Autowired
    BalanceService balanceService;

    @MockBean
    BalanceRepository balanceRepository;

    @BeforeEach//когда нужно писать одно и тоже в тестовых методах
    void setUp () {

    }
    @Test
    void createBalance() {
        Balance balance = new Balance();
        BigDecimal bigDecimal = new BigDecimal(10);
        balance.setAmountMoney(bigDecimal);//инициализация
        Mockito.when(balanceRepository.save(balance)).thenReturn(balance);
        Balance newBalance = balanceService.createBalance(balance); // то что тестируем
        Assertions.assertEquals(balance, newBalance);//проверка
    }
}
