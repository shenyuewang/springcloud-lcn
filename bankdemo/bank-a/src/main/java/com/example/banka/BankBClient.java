package com.example.banka;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "bank-b")
public interface BankBClient {

    @GetMapping("/add-money")
    String addMoney(@RequestParam("money") int money,
                           @RequestParam("user")String user);
}
