package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.account.AccountCDTO;
import design.kfu.sunrise.domain.dto.account.AccountPartnerCDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.Exc;
import design.kfu.sunrise.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    @Lazy
    @Autowired
    private ClubService clubService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Account addAccount(AccountCDTO accountCDTO){
        accountCDTO.setPassword(passwordEncoder.encode(accountCDTO.getPassword()));
        Account account = AccountCDTO.toAccount(accountCDTO);
        account.setRole(Account.Role.USER);
        return accountRepository.save(account);
    }

    @Override
    public Account addPartnerAccount(AccountPartnerCDTO accountPartnerCDTO) {
        accountPartnerCDTO.setPassword(passwordEncoder.encode(accountPartnerCDTO.getPassword()));
        Account account = AccountPartnerCDTO.toAccount(accountPartnerCDTO);
        account.setRole(Account.Role.PARTNER);
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public Account getAccount(Long accountId){
        return accountRepository.getById(accountId);
    }

    @Override
    public Account findOrThrow(Long userId) {
        return accountRepository
                .findById(userId)
                .orElseThrow(Exc.sup(ErrorType.ENTITY_NOT_FOUND,"Сущность пользователя не найдена"));
    }

    @Override
    public Account updateAccount(Account account) {
        accountRepository.save(account);
        return account;
    }

    @Override
    public Set<Club> getCreatedClubs(Account account) {
        return clubService.findAllByAuthor(account);
    }
}
