package com.vignesh.howzat.api;

import com.vignesh.howzat.model.Handshake;
import com.vignesh.howzat.model.SignInInfo;
import com.vignesh.howzat.model.SignUpInfo;
import com.vignesh.howzat.model.UserKeys;
import com.vignesh.howzat.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Handshake sayHello() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        return accountService.sayHello(timeInMillis, "connection established");
    }

    @GetMapping(path = "generate-user-keys")
    public UserKeys generateUserKeys(@RequestParam("no_of_teams") int noOfTeams) {
        return accountService.generateUserKeys(noOfTeams);
    }

    @PostMapping(path = "signUp")
    public SignUpInfo signUp(@RequestParam("username") String userName,
                             @RequestParam("password") String password,
                             @RequestParam("user_key") String userKey) {
        return accountService.signUp(userName, password, userKey);
    }

    @PostMapping(path = "signin/{username}")
    public SignInInfo signIn(@PathVariable("username") String userName, @RequestParam("password") String password) {
        return accountService.signIn(userName, password);
    }
}
