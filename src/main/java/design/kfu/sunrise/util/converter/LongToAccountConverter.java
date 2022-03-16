package design.kfu.sunrise.util.converter;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Daniyar Zakiev
 */
@Slf4j
public class LongToAccountConverter implements Converter<Long, Account> {

    @Autowired
    private AccountService accountService;

    @Override
    public Account convert(Long accountId) {
        return accountService.findOrThrow(accountId);
    }
}
