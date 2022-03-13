package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.dto.AccountCDTO;
import design.kfu.sunrise.domain.dto.AccountVDTO;
import design.kfu.sunrise.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PermitAll
    @PostMapping("/account")
    public AccountVDTO saveAccount(AccountCDTO accountCDTO){
        return accountService.saveAccount(accountCDTO);
    }

}
