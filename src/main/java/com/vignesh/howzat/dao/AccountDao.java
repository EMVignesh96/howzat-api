package com.vignesh.howzat.dao;

import com.vignesh.howzat.model.Handshake;
import com.vignesh.howzat.model.SignInInfo;
import com.vignesh.howzat.model.SignUpInfo;
import com.vignesh.howzat.model.UserKeys;

public interface AccountDao {
    Handshake sayHello(long timeInMillis, String message);

    UserKeys generateUserKeys(int noOfTeams);

    SignUpInfo signUp(String userName, String password, String userKey);

    SignInInfo signIn(String userName, String password);
}
