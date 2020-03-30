package com.vignesh.howzat.service;

import com.vignesh.howzat.dao.AccountDao;
import com.vignesh.howzat.model.Handshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountDao accountDao;

    @Autowired
    public AccountService(@Qualifier("postgres") AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Handshake sayHello(long timeInMillis, String message) {
        return accountDao.sayHello(timeInMillis, message);
    }
}
