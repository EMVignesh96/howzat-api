package com.vignesh.howzat.dao;

import com.vignesh.howzat.model.Handshake;
import org.springframework.stereotype.Repository;

@Repository("mock")
public class MockAccountDataAccessService implements AccountDao {

    @Override
    public Handshake sayHello(long timeInMillis, String message) {
        return new Handshake(timeInMillis, message + "; data from MockAccountDataAccessService");
    }
}
