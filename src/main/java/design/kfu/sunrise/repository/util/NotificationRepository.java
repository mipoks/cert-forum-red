package design.kfu.sunrise.repository.util;

import design.kfu.sunrise.domain.model.util.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
    List<Notification> findByAccountId(String accountId);
}
