package design.kfu.sunrise.service.mail.util;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.util.ActivationCode;
@Deprecated
public interface ActivationCodeService {
    ActivationCode generate(Account account);

    ActivationCode save(ActivationCode code);
}
