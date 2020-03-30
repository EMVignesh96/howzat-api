package com.vignesh.howzat.dao;

import com.vignesh.howzat.model.Handshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("postgres")
public class AccountDataAccessService implements AccountDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Handshake sayHello(long timeInMillis, String message) {
        return new Handshake(timeInMillis, message + "; data from AccountDataAccessService");
    }
}
