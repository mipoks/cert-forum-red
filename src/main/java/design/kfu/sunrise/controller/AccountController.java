package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.dto.AccountCDTO;
import design.kfu.sunrise.domain.dto.AccountVDTO;
import design.kfu.sunrise.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PermitAll
    @PostMapping("/account")
    public AccountVDTO saveAccount(@RequestBody AccountCDTO accountCDTO) {
        log.info("accoutnDTO {}",accountCDTO);
        return accountService.saveAccount(accountCDTO);
    }

    @GetMapping("/account/{account_id}")
    public AccountVDTO getAccount(@PathVariable("account_id") Long accountId) {
        return accountService.getAccount(accountId);
    }
}
