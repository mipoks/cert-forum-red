package design.kfu.sunrise.service.mail.context;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.util.ActivationCode;
import design.kfu.sunrise.repository.util.ActivationCodeRepository;
import design.kfu.sunrise.service.AccountService;
import design.kfu.sunrise.service.mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
@Deprecated
@Service
public class EmailServiceImpl implements EmailService {

    public static final String INCORRECT_CODE = "Вы перешли по недействительной ссылке";
    public static final String EMAIL_CONFIRMED = "Ваш Email подвержден";
    @Autowired
    private ActivationCodeRepository activationCodeRepository;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private AccountService accountService;
    @Autowired
    private SpringTemplateEngine templateEngine;

    //ToDo проверить Async
    @Async
    @Override
    public CompletableFuture<Boolean> sendEmail(AbstractEmailContext emailContext) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(emailContext.getContext());
        String emailContent = templateEngine.process(emailContext.getTemplateLocation(), context);

        mimeMessageHelper.setTo(emailContext.getTo());
        mimeMessageHelper.setSubject(emailContext.getSubject());
        mimeMessageHelper.setFrom(emailContext.getFrom());
        mimeMessageHelper.setText(emailContent, true);
        emailSender.send(message);
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

    @Override
    public String confirmEmail(String code) {
        String result;
        Optional<ActivationCode> activationCode = activationCodeRepository.findById(code);
        if (activationCode.isEmpty()) {
            result = INCORRECT_CODE;
        } else {
            Account account = accountService.findOrThrow(activationCode.get().getAccountId());
            account.getAccountInfo().setEmailConfirmed(true);
            accountService.updateAccount(account);
            result = EMAIL_CONFIRMED;
            activationCodeRepository.deleteById(code);
        }
        return result;
    }
}
