package com.vignesh.howzat.dao;

import com.vignesh.howzat.auth.AppUser;
import com.vignesh.howzat.exception.signup.DuplicateUserKeyException;
import com.vignesh.howzat.exception.signup.UserAlreadySignedUpException;
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
public class HowzatDataAccessService implements HowzatDao {

    private static final String TAB_USERS = "users";
    private static final String TAB_AUTHORITIES = "authorities";
    private static final String TAB_USER_KEYS = "user_keys";
    private static final String COL_USER_KEY = "u_key";
    private static final String COL_USER_NAME = "username";
    private static final String COL_AUTHORITY = "authority";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HowzatDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Handshake sayHello(long timeInMillis, String message) {
        return new Handshake(timeInMillis, message + "; data from AccountDataAccessService");
    }

    @Override
    public UserKeys generateUserKeys(int noOfTeams) {
        UserKeys userKeys = new UserKeys();
        Random random = new Random();
        for (int i = 0; i < noOfTeams; i++) {
            String digest = DigestUtils.md5DigestAsHex(String.valueOf(random.nextLong()).getBytes());
            String userKey = digest.substring(0, 6);
            userKeys.addKey(userKey);
            jdbcTemplate.update("INSERT INTO " + TAB_USER_KEYS + " (" + COL_USER_KEY + ") VALUES (?)", userKey);
        }
        return userKeys;
    }

    @Override
    public SignUpInfo signUpBuyer(AppUser user, String userKey) {
        List<Map<String, Object>> mapList = jdbcTemplate
                .queryForList("SELECT * FROM " + TAB_USER_KEYS + " WHERE " + COL_USER_KEY + " = ?", userKey);

        if (mapList.size() == 0) throw new UserKeyNotFoundException("User Key Not Found");

        if (mapList.get(0).get(COL_USER_NAME) != null)
            throw new DuplicateUserKeyException("User Key already registered");

        try {
            insertUser(user);
            jdbcTemplate.update("UPDATE " + TAB_USER_KEYS + " SET " + COL_USER_NAME + " = ? WHERE "
                    + COL_USER_KEY + " = ?", user.getUsername(), userKey);
            return new SignUpInfo(user.getUsername(), "Account created");
        } catch (DuplicateKeyException e) {
            throw new UserNameAlreadyExistException("User name already exist");
        }
    }

    @Override
    public SignUpInfo signUpAuctioneer(AppUser auctioneer) {
        List<Map<String, Object>> resultList = jdbcTemplate
                .queryForList("SELECT * FROM " + TAB_AUTHORITIES + " WHERE " + COL_AUTHORITY + " = 'ROLE_AUCTIONEER'");
        if (resultList.size() != 0) throw new UserAlreadySignedUpException("Auctioneer already signed up");
        try {
            insertUser(auctioneer);
        } catch (DuplicateKeyException e) {
            throw new UserNameAlreadyExistException("User name already exist");
        }
        return new SignUpInfo(auctioneer.getUsername(), "Account created");
    }

    private void insertUser(AppUser user) {
        jdbcTemplate.update("INSERT INTO " + TAB_USERS + " VALUES (?, ?, ?)",
                user.getUsername(), user.getPassword(), user.isEnabled());
        user.getAuthorities().forEach(authority -> jdbcTemplate.update("INSERT INTO " + TAB_AUTHORITIES + " VALUES(?, ?)",
                user.getUsername(), authority.getAuthority()));
    }
}
