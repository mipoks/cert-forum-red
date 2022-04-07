package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.util.Notification;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
@Service
public class NotificationServiceImpl implements NotificationService {


    //ToDo сохранить в репозиторий notification, где ключ будет account id
    @Override
    public void notifyAccount(Notification notification, Account account) {

    }

    @Override
    public void notifyClub(Notification notification, Club club) {
        Set<Account> accounts = club.getAccounts();
        for (Account account : accounts) {
            notifyAccount(notification, account);
        }
        Account author = club.getAuthor();
        boolean authorInClub = accounts.stream().anyMatch(a -> a.equals(author));
        if (!authorInClub) {
            notifyAccount(notification, author);
        }
    }

    //Выдавать только непрочитанные
    @Override
    public Set<Notification> findByAccount(Account account) {
        return null;
    }
}
