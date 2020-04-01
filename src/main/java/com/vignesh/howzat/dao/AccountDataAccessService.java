package com.vignesh.howzat.dao;

import com.vignesh.howzat.model.Handshake;
import com.vignesh.howzat.model.UserKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import java.util.Random;

@Repository("postgres")
public class AccountDataAccessService implements AccountDao {

    private static final String TAB_ACCOUNT = "account";
    private static final String COL_USER_KEY = "user_key";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Handshake sayHello(long timeInMillis, String message) {
        return new Handshake(timeInMillis, message + "; data from AccountDataAccessService");
    }

    @Override
    public UserKeys generateUserKeys(int noOfTeams) {
        deleteAllFromAccountTable();

        UserKeys userKeys = new UserKeys();
        Random random = new Random();
        for (int i = 0; i < noOfTeams; i++) {
            String digest = DigestUtils.md5DigestAsHex(String.valueOf(random.nextLong()).getBytes());
            String userKey = digest.substring(0, 6);
            userKeys.addKey(userKey);
            insertIntoAccountTable(userKey);
        }

        return userKeys;
    }

    private void deleteAllFromAccountTable() {
        jdbcTemplate.update("DELETE FROM " + TAB_ACCOUNT);
    }

    private void insertIntoAccountTable(String userKey) {
        jdbcTemplate.update("INSERT INTO " + TAB_ACCOUNT + " ( " + COL_USER_KEY + " ) VALUES ( ? )", userKey);
    }


}
