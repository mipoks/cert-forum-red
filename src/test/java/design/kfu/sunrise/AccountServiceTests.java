package design.kfu.sunrise;

import design.kfu.sunrise.domain.dto.account.AccountCDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.service.AccountService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountServiceTests {

    /*
    Account addAccount(AccountCDTO accountCDTO);
    Account addPartnerAccount(AccountPartnerCDTO accountPartnerCDTO);
    Account getAccount(Long accountId);
    Account findOrThrow(Long accountId);
    Account updateAccount(Account account);
    //Возвращает все созданные этим пользователем клубы
    Set<Club> getCreatedClubs(Account account);
     */

    @Autowired
    private AccountService accountService;
    
}
