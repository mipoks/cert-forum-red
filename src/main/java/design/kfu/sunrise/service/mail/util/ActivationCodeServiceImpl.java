package design.kfu.sunrise.service.mail.util;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.util.ActivationCode;
import design.kfu.sunrise.repository.util.ActivationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Daniyar Zakiev
 */
@Service
public class ActivationCodeServiceImpl implements ActivationCodeService {
    @Autowired
    private ActivationCodeRepository activationCodeRepository;

    @Override
    public ActivationCode generate(Account account) {
        ActivationCode ac = ActivationCode.builder()
                .accountId(account.getId())
                .code(account.getLogin() + "test")
                .build();
        return ac;
    }

    @Override
    public ActivationCode save(ActivationCode code) {
        return activationCodeRepository.save(code);
    }
}
