package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.account.AccountCDTO;
import design.kfu.sunrise.domain.dto.account.AccountPartnerCDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface AccountService {
    @Transactional
    Account addAccount(AccountCDTO accountCDTO);

    @Transactional
    Account addPartnerAccount(AccountPartnerCDTO accountPartnerCDTO);

    @Transactional
    Account getAccount(Long accountId);

    Account findOrThrow(Long accountId);

    Account updateAccount(Account account);

    //Возвращает все созданные этим пользователем клубы
    Set<Club> getCreatedClubs(Account account);

    Account getAccountByUsername(String name);
}
