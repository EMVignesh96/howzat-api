package com.vignesh.howzat.service;

import com.vignesh.howzat.auth.AppUser;
import com.vignesh.howzat.dao.AccountDao;
import com.vignesh.howzat.model.Handshake;
import com.vignesh.howzat.model.SignInInfo;
import com.vignesh.howzat.model.SignUpInfo;
import com.vignesh.howzat.model.UserKeys;
import com.vignesh.howzat.security.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.vignesh.howzat.security.UserRole.BUYER;

@Service
public class AccountService {
    private final AccountDao accountDao;
    private final PasswordConfig passwordConfig;

    @Autowired
    public AccountService(@Qualifier("postgres") AccountDao accountDao, PasswordConfig passwordConfig) {
        this.accountDao = accountDao;
        this.passwordConfig = passwordConfig;
    }

    public Handshake sayHello(long timeInMillis, String message) {
        return accountDao.sayHello(timeInMillis, message);
    }

    public UserKeys generateUserKeys(int noOfTeams) {
        return accountDao.generateUserKeys(noOfTeams);
    }

    public SignUpInfo signUpUser(String userName, String password, String userKey) {
        AppUser buyer = new AppUser(userName,
                passwordConfig.passwordEncoder().encode(password),
                BUYER.getGrantedAuthorities(),
                true,
                true,
                true,
                true);
        return accountDao.signUpUser(buyer, userKey);
    }

    public SignInInfo signInUser(String userName, String password) {
        return accountDao.signInUser(userName, password);
    }
}
