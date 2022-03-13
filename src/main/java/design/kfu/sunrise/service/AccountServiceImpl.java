package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.AccountCDTO;
import design.kfu.sunrise.domain.dto.AccountVDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.Exc;
import design.kfu.sunrise.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AccountVDTO saveAccount(AccountCDTO accountCDTO){
        accountCDTO.setPassword(passwordEncoder.encode(accountCDTO.getPassword()));
        Account account = AccountCDTO.toAccount(accountCDTO);
        account.setRole(Account.Role.USER); //fixme
        return AccountVDTO.from(accountRepository.save(account));
    }

    @Override
    @Transactional
    public AccountVDTO getAccount(Long accountId){
        return AccountVDTO.from(accountRepository.getById(accountId));
    }

    @Override
    public Account findOrThrow(Long userId) {
        return accountRepository
                .findById(userId)
                .orElseThrow(Exc.sup(ErrorType.ENTITY_NOT_FOUND,"Сущность пользователя не найдена"));
    }

}
