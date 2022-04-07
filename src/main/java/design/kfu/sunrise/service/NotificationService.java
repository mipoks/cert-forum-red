package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.util.Notification;

/**
 * @author Daniyar Zakiev
 */
public interface NotificationService {
    void notificateAccount(Notification notification, Account account);
    void notificateClub(Notification notification, Club club);
}
