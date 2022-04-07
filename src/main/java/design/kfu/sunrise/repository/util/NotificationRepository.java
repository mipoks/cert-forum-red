package design.kfu.sunrise.repository.util;

import design.kfu.sunrise.domain.model.util.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Daniyar Zakiev
 */
public interface NotificationRepository extends CrudRepository<Notification, String> {
    List<Notification> findByAccountId(Long accountId);
}
