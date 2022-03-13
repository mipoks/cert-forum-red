package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.dto.AccountCDTO;
import design.kfu.sunrise.domain.dto.AccountVDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    @PostMapping("/account")
    public AccountVDTO saveAccount(AccountCDTO accountCDTO){
        //Todo
    }

}
