package com.vignesh.howzat.dao;

import com.vignesh.howzat.model.Handshake;

public interface AccountDao {
    Handshake sayHello(long timeInMillis, String message);
}
