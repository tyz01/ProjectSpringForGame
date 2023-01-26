package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.models.Transfer;
import com.bezkoder.spring.security.postgresql.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/transfer")
@AllArgsConstructor
@RestController
public class TransferController {

    private TransferService transferBalanceService;

    @PostMapping("/")
    public void transferBalance(@RequestBody Transfer transferBalance) {
        transferBalanceService.transferBalance(transferBalance);
    }
}
