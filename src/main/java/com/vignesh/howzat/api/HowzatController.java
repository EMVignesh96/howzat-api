package com.vignesh.howzat.api;

import com.vignesh.howzat.model.Handshake;
import com.vignesh.howzat.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RequestMapping("api/v1")
@RestController
public class HowzatController {
    private final AccountService accountService;

    @Autowired
    public HowzatController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "hello")
    public Handshake sayHello() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        return accountService.sayHello(timeInMillis, "connection established");
    }
}
