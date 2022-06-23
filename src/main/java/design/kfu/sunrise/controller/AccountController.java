package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.dto.account.AccountCDTO;
import design.kfu.sunrise.domain.dto.account.AccountPartnerCDTO;
import design.kfu.sunrise.domain.dto.account.AccountUpdateDTO;
import design.kfu.sunrise.domain.dto.account.AccountVDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.service.AccountService;
import design.kfu.sunrise.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PermitAll
    @GetMapping("/{account_id}")
    public AccountVDTO getAccount(@PathVariable("account_id") Account account) {
        return AccountVDTO.from(account);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping()
    public AccountVDTO findAccountByEmail(@AuthenticationPrincipal Jwt principal) {
        log.info("jwt auth is {}", principal.getTokenValue());
        log.info("auth {}", principal.getClaims());
        log.info("auth {}", principal.getHeaders());
        log.info("tokenuser {}", SecurityUtils.getUser());
        log.info("user roles {}", SecurityUtils.getUser().get().getRoles());
        log.info("is auth bool {}", SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        return AccountVDTO.from(
                Account.builder()
                        .id(principal.getId())
                        .email(SecurityUtils.getUser().get().getEmail())
                        .build());
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping
    public AccountVDTO updateAccount(@RequestBody @Valid AccountUpdateDTO accountUpdateDTO, @AuthenticationPrincipal(expression = "account") Account account) {
        account.getAccountInfo().setPhone(accountUpdateDTO.getPhone());
        return AccountVDTO.from(accountService.updateAccount(account));
    }

}
