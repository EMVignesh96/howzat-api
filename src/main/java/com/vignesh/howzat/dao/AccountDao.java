package com.vignesh.howzat.dao;

import com.vignesh.howzat.model.Handshake;
import com.vignesh.howzat.model.UserKeys;

public interface AccountDao {
    Handshake sayHello(long timeInMillis, String message);

    UserKeys generateUserKeys(int noOfTeams);
}
