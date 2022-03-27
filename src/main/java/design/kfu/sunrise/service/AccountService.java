package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.AccountCDTO;
import design.kfu.sunrise.domain.dto.AccountVDTO;
import design.kfu.sunrise.domain.model.Account;
import org.springframework.transaction.annotation.Transactional;

public interface AccountService {
    @Transactional
    AccountVDTO saveAccount(AccountCDTO accountCDTO);

    @Transactional
    AccountVDTO getAccount(Long accountId);
    Account findOrThrow(Long accountId);

    AccountVDTO updateAccount(Account account);
}
