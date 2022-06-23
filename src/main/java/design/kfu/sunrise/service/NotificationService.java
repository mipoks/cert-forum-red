package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.util.Notification;

import java.util.Set;

public interface NotificationService {
    void notifyAccount(Notification notification, Account account);

    void notifyClub(Notification notification, Club club);

    Set<Notification> findByAccount(Account account);

    void markAsRed(Notification notification);

    void markAsNotRed(Notification notification);
}
