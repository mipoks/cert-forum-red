package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.AccountCDTO;
import design.kfu.sunrise.domain.dto.AccountVDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface AccountService {
    @Transactional
    AccountVDTO saveAccount(AccountCDTO accountCDTO);

    @Transactional
    AccountVDTO getAccount(Long accountId);
    Account findOrThrow(Long accountId);

    AccountVDTO updateAccount(Account account);

    //Возвращает все созданные этим пользователем аккаунты
    Set<Club> getCreatedClubs(Account account);
}
