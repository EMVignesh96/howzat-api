package com.vignesh.howzat.service;

import com.vignesh.howzat.dao.AccountDao;
import com.vignesh.howzat.model.Handshake;
import com.vignesh.howzat.model.SignInInfo;
import com.vignesh.howzat.model.SignUpInfo;
import com.vignesh.howzat.model.UserKeys;
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

    public UserKeys generateUserKeys(int noOfTeams) {
        return accountDao.generateUserKeys(noOfTeams);
    }

    public SignUpInfo signUp(String userName, String password, String userKey) {
        return accountDao.signUp(userName, password, userKey);
    }

    public SignInInfo signIn(String userName, String password) {
        return accountDao.signIn(userName, password);
    }
}
