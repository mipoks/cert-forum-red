package design.kfu.sunrise.service.mail.util;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.util.ActivationCode;
import design.kfu.sunrise.service.mail.context.AbstractEmailContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Daniyar Zakiev
 */
@Service
public class EmailContextGenerator {

    @Value("${spring.mail.username}")
    private String fromEmail;

    public AbstractEmailContext generateConfirmationContext(Account account, ActivationCode activationCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", account.getLogin());
        map.put("verificationURL", "http://localhost:8080/v1/account/email?code=" + activationCode.getCode());
        return AbstractEmailContext.builder()
                .emailLanguage("en")
                .to(account.getLogin())
                .from(fromEmail)
                .subject("Email Confirmation")
                .templateLocation("TestEmail.html")
                .context(map)
                .build();
    }
}
