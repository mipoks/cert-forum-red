package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.dto.account.AccountCDTO;
import design.kfu.sunrise.domain.dto.account.AccountPartnerCDTO;
import design.kfu.sunrise.domain.dto.account.AccountUpdateDTO;
import design.kfu.sunrise.domain.dto.account.AccountVDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "v1")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PermitAll
    @PostMapping("/account")
    public AccountVDTO addAccount(@RequestBody @Valid AccountCDTO accountCDTO) {
        log.info("accountDTO {}",accountCDTO);
        Account account = accountService.addAccount(accountCDTO);
        return AccountVDTO.from(account);
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("/account")
    public AccountVDTO updateAccount(@RequestBody @Valid AccountUpdateDTO accountUpdateDTO, @AuthenticationPrincipal(expression = "account") Account account) {
        account.setLogin(accountUpdateDTO.getEmail());
        account.getAccountInfo().setEmailConfirmed(false);
        account.getAccountInfo().setPhone(accountUpdateDTO.getPhone());
        if (account.getHashPassword().equals(passwordEncoder.encode(accountUpdateDTO.getOldPassword()))) {
            account.setHashPassword(passwordEncoder.encode(accountUpdateDTO.getNewPassword()));
        }
        return AccountVDTO.from(accountService.updateAccount(account));
    }

}
