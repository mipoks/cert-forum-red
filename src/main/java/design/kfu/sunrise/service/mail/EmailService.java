package design.kfu.sunrise.service.mail;

import design.kfu.sunrise.service.mail.context.AbstractEmailContext;

import javax.mail.MessagingException;

/**
 * @author Daniyar Zakiev
 */
public interface EmailService {
    void sendEmail(AbstractEmailContext emailContext) throws MessagingException;
}
