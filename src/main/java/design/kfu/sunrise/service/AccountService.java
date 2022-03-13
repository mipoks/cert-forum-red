package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.AccountCDTO;
import design.kfu.sunrise.domain.dto.AccountVDTO;
import org.springframework.transaction.annotation.Transactional;

public interface AccountService {
    @Transactional
    AccountVDTO saveAccount(AccountCDTO accountCDTO);
}
