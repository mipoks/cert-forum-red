package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.dto.AccountCDTO;
import design.kfu.sunrise.domain.dto.AccountPartnerCDTO;
import design.kfu.sunrise.domain.dto.AccountVDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1")
public class AccountController {
    private final AccountService accountService;

    @PermitAll
    @PostMapping("/account")
    public AccountVDTO addAccount(@RequestBody @Valid AccountCDTO accountCDTO) {
        log.info("accountDTO {}",accountCDTO);
        return AccountVDTO.from(accountService.addAccount(accountCDTO));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/account/{account_id}")
    public AccountVDTO getAccount(@PathVariable("account_id") Account account) {
        return AccountVDTO.from(account);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/account/partner")
    public AccountVDTO addPartnerAccount(@RequestBody @Valid AccountPartnerCDTO accountPartnerCDTO) {
        return AccountVDTO.from(accountService.addPartnerAccount(accountPartnerCDTO));
    }

}
