package com.vignesh.howzat.dao;

import com.vignesh.howzat.exception.signup.DuplicateUserKeyException;
import com.vignesh.howzat.exception.signup.UserKeyNotFoundException;
import com.vignesh.howzat.exception.signup.UserNameAlreadyExistException;
import com.vignesh.howzat.model.Handshake;
import com.vignesh.howzat.model.SignUpInfo;
import com.vignesh.howzat.model.UserKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Repository("postgres")
public class AccountDataAccessService implements AccountDao {

    private static final String TAB_ACCOUNT = "account";
    private static final String COL_USER_KEY = "user_key";
    private static final String COL_USER_NAME = "username";
    private static final String COL_PASSWORD_HASH = "password_hash";
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

    @Override
    public SignUpInfo signUp(String userName, String password, String userKey) {
        if (isUserKeyNotFound(userKey)) throw new UserKeyNotFoundException("User Key Not Found");
        if (isUserKeyDuplicate(userKey)) throw new DuplicateUserKeyException("User Key already registered");

        try {
            String sql = "UPDATE " + TAB_ACCOUNT + " SET " + COL_USER_NAME + " = ?, " + COL_PASSWORD_HASH + " = ? WHERE " + COL_USER_KEY + " = ?";
            String passwordHash = DigestUtils.md5DigestAsHex(password.getBytes());
            jdbcTemplate.update(sql, userName, passwordHash, userKey);
            return new SignUpInfo(userName, "Account created");
        } catch (DuplicateKeyException e) {
            throw new UserNameAlreadyExistException("User name already exist");
        }
    }

    private boolean isUserKeyDuplicate(String userKey) {
        String sql = "SELECT " + COL_USER_NAME + " FROM " + TAB_ACCOUNT + " WHERE " + COL_USER_KEY + " = ?";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, userKey);
        return mapList.get(0).get(COL_USER_NAME) != null;
    }

    private boolean isUserKeyNotFound(String userKey) {
        String sql = "SELECT " + COL_USER_KEY + " FROM " + TAB_ACCOUNT + " WHERE " + COL_USER_KEY + " = ?";
        return jdbcTemplate.queryForList(sql, userKey).size() == 0;
    }

    private void deleteAllFromAccountTable() {
        jdbcTemplate.update("DELETE FROM " + TAB_ACCOUNT);
    }

    private void insertIntoAccountTable(String userKey) {
        jdbcTemplate.update("INSERT INTO " + TAB_ACCOUNT + " ( " + COL_USER_KEY + " ) VALUES ( ? )", userKey);
    }


}
