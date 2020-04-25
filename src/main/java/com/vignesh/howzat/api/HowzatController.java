package com.vignesh.howzat.api;

import com.vignesh.howzat.model.Handshake;
import com.vignesh.howzat.model.SignUpInfo;
import com.vignesh.howzat.model.UserKeys;
import com.vignesh.howzat.service.HowzatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RequestMapping("api/v1")
@RestController
public class HowzatController {
    private final HowzatService howzatService;

    @Autowired
    public HowzatController(HowzatService howzatService) {
        this.howzatService = howzatService;
    }

    @GetMapping(path = "/buyer/hello")
    public Handshake sayHello() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        return howzatService.sayHello(timeInMillis, "connection established");
    }

    @GetMapping(path = "/auctioneer/generateUserKeys/{no_of_teams}")
    public UserKeys generateUserKeys(@PathVariable("no_of_teams") int noOfTeams) {
        return howzatService.generateUserKeys(noOfTeams);
    }

    @PostMapping(path = "/buyer/signUp")
    public SignUpInfo signUpUser(@RequestParam("username") String userName,
                                 @RequestParam("password") String password,
                                 @RequestParam("user_key") String userKey) {
        return howzatService.signUpBuyer(userName, password, userKey);
    }

    @PostMapping(path = "/auctioneer/signUp")
    public SignUpInfo signUpAuctioneer(@RequestParam("username") String userName,
                                       @RequestParam("password") String password) {
        return howzatService.signUpAuctioneer(userName, password);
    }
}
