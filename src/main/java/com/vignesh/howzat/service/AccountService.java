package com.vignesh.howzat.service;

import com.vignesh.howzat.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountDao accountDao;

    @Autowired
    public AccountService(@Qualifier("mock") AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
