package com.vignesh.howzat.dao;

import com.vignesh.howzat.auth.AppUser;
import com.vignesh.howzat.model.Handshake;
import com.vignesh.howzat.model.Player;
import com.vignesh.howzat.model.SignUpInfo;
import com.vignesh.howzat.model.UserKeys;

public interface HowzatDao {
    Handshake sayHello(long timeInMillis, String message);

    UserKeys generateUserKeys(int noOfTeams);

    SignUpInfo signUpBuyer(AppUser buyer, String userKey);

    SignUpInfo signUpAuctioneer(AppUser auctioneer);

    Player getCurrentPlayer();
}
