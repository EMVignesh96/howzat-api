package com.vignesh.howzat.service;

import com.vignesh.howzat.auth.AppUser;
import com.vignesh.howzat.dao.HowzatDao;
import com.vignesh.howzat.model.Handshake;
import com.vignesh.howzat.model.SignUpInfo;
import com.vignesh.howzat.model.UserKeys;
import com.vignesh.howzat.security.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.vignesh.howzat.security.UserRole.AUCTIONEER;
import static com.vignesh.howzat.security.UserRole.BUYER;

@Service
public class HowzatService {

    private final HowzatDao howzatDao;
    private final PasswordConfig passwordConfig;

    @Autowired
    public HowzatService(@Qualifier("postgres") HowzatDao howzatDao, PasswordConfig passwordConfig) {
        this.howzatDao = howzatDao;
        this.passwordConfig = passwordConfig;
    }

    public Handshake sayHello(long timeInMillis, String message) {
        return howzatDao.sayHello(timeInMillis, message);
    }

    public UserKeys generateUserKeys(int noOfTeams) {
        return howzatDao.generateUserKeys(noOfTeams);
    }

    public SignUpInfo signUpBuyer(String userName, String password, String userKey) {
        AppUser buyer = new AppUser(userName,
                passwordConfig.passwordEncoder().encode(password),
                BUYER.getGrantedAuthorities(),
                true,
                true,
                true,
                true);
        return howzatDao.signUpBuyer(buyer, userKey);
    }

    public SignUpInfo signUpAuctioneer(String userName, String password) {
        AppUser auctioneer = new AppUser(userName,
                passwordConfig.passwordEncoder().encode(password),
                AUCTIONEER.getGrantedAuthorities(),
                true,
                true,
                true,
                true);
        return howzatDao.signUpAuctioneer(auctioneer);
    }
}
