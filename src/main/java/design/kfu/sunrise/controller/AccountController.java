package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.dto.AccountCDTO;
import design.kfu.sunrise.domain.dto.AccountPartnerCDTO;
import design.kfu.sunrise.domain.dto.AccountVDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.util.ActivationCode;
import design.kfu.sunrise.service.AccountService;
import design.kfu.sunrise.service.mail.EmailService;
import design.kfu.sunrise.service.mail.util.ActivationCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "v1")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ActivationCodeService activationCodeService;

    @PermitAll
    @PostMapping("/account")
    public AccountVDTO addAccount(@RequestBody @Valid AccountCDTO accountCDTO) {
        log.info("accountDTO {}",accountCDTO);
        Account account = accountService.addAccount(accountCDTO);
        AccountVDTO accountVDTO = AccountVDTO.from(account);
        ActivationCode activationCode = activationCodeService.generate(account);
        activationCodeService.save(activationCode);
        return accountVDTO;
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
