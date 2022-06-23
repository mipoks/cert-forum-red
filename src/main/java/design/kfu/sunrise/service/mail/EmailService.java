package design.kfu.sunrise.service.mail;

import design.kfu.sunrise.service.mail.context.AbstractEmailContext;

import javax.mail.MessagingException;
import java.util.concurrent.CompletableFuture;
@Deprecated
public interface EmailService {

    CompletableFuture<Boolean> sendEmail(AbstractEmailContext emailContext) throws MessagingException;

    String confirmEmail(String code);
}
