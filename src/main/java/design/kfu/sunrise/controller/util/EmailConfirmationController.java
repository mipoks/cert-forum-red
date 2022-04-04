package design.kfu.sunrise.controller.util;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.util.ActivationCode;
import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.RestException;
import design.kfu.sunrise.service.mail.EmailService;
import design.kfu.sunrise.service.mail.context.AbstractEmailContext;
import design.kfu.sunrise.service.mail.util.ActivationCodeService;
import design.kfu.sunrise.service.mail.util.EmailContextGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.constraints.NotNull;
import java.util.concurrent.CompletableFuture;

/**
 * @author Daniyar Zakiev
 */
@Slf4j
@RestController
@RequestMapping("v1")
public class EmailConfirmationController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ActivationCodeService activationCodeService;

    @Autowired
    private EmailContextGenerator emailContextGenerator;

    @GetMapping("/account/email")
    public String confirmEmail(@RequestParam(name = "activate") @NotNull String code) {
        return emailService.confirmEmail(code);
    }

    //ToDo проверить, что в отдельном потоке
    @PostMapping("/account/email")
    public CompletableFuture<Boolean> sendConfirmationEmail(@AuthenticationPrincipal(expression = "account") Account account) {
        ActivationCode activationCode = activationCodeService.generate(account);
        activationCodeService.save(activationCode);

        AbstractEmailContext abstractEmailContext = emailContextGenerator.generateConfirmationContext(account, activationCode);
        try {
            return emailService.sendEmail(abstractEmailContext);
        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw new RestException(ErrorType.UNEXPECTED_ERROR, "Не удалось отправить письмо с кодом подтверждения");
        }
    }

}
