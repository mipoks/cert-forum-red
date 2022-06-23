package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.account.AccountCDTO;
import design.kfu.sunrise.domain.dto.account.AccountPartnerCDTO;
import design.kfu.sunrise.domain.event.AccountEvent;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.Exc;
import design.kfu.sunrise.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    private ApplicationEventPublisher publisher;

    @Lazy
    @Autowired
    private ClubService clubService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Account getAccount(String accountId) {
        return accountRepository.getById(accountId);
    }

    @Override
    public Account findOrThrow(String userId) {
        return accountRepository
                .findById(userId)
                .orElseThrow(Exc.sup(ErrorType.ENTITY_NOT_FOUND, "Сущность пользователя не найдена"));
    }

    @Override
    public Account updateAccount(Account account) {
        Account updated = accountRepository.save(account);
        publisher.publishEvent(new AccountEvent(Account.class.getName(), AccountEvent.Event.UPDATE.getName(), updated));
        return updated;
    }

    @Override
    public Set<Club> getCreatedClubs(Account account) {
        return clubService.findAllByAuthor(account);
    }

    @Override
    public Account getAccountByUsername(String name) {
        return accountRepository
                .getAccountByEmail(name).orElseThrow(Exc.sup(ErrorType.ENTITY_NOT_FOUND));
    }
}
