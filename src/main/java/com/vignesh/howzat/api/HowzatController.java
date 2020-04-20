package com.vignesh.howzat.api;

import com.vignesh.howzat.model.Handshake;
import com.vignesh.howzat.model.SignInInfo;
import com.vignesh.howzat.model.SignUpInfo;
import com.vignesh.howzat.model.UserKeys;
import com.vignesh.howzat.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RequestMapping("api/v1")
@RestController
public class HowzatController {
    private final AccountService accountService;

    @Autowired
    public HowzatController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "hello")
    @PreAuthorize("hasAuthorities('ROLE_BUYER')")
    public Handshake sayHello() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        return accountService.sayHello(timeInMillis, "connection established");
    }

    @GetMapping(path = "/auctioneer/generateUserKeys")
    public UserKeys generateUserKeys(@RequestParam("no_of_teams") int noOfTeams) {
        return accountService.generateUserKeys(noOfTeams);
    }

    @PostMapping(path = "/buyer/signUp")
    public SignUpInfo signUpUser(@RequestParam("username") String userName,
                                 @RequestParam("password") String password,
                                 @RequestParam("user_key") String userKey) {
        return accountService.signUpUser(userName, password, userKey);
    }

    @PostMapping(path = "/buyer/signIn")
    public SignInInfo signInUser(@RequestParam("username") String userName,
                                 @RequestParam("password") String password) {
        return accountService.signInUser(userName, password);
    }

    @PostMapping(path = "/auctioneer/signUp")
    public SignUpInfo signUpAuctioneer(@RequestParam("username") String userName,
                                       @RequestParam("password") String password,
                                       @RequestParam("user_key") String userKey) {
        return accountService.signUpUser(userName, password, userKey);
    }

    @PostMapping(path = "/auctioneer/signIn")
    public SignInInfo signInAuctioneer(@RequestParam("username") String userName,
                                       @RequestParam("password") String password) {
        return accountService.signInUser(userName, password);
    }
}
