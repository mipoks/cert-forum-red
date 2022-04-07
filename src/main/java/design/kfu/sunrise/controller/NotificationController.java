package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.util.Notification;
import design.kfu.sunrise.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
@RestController(value = "v1")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notifications")
    public Set<Notification> getNotifications(@AuthenticationPrincipal(expression = "account") Account account) {
        return notificationService.findByAccount(account);
    }

    @PutMapping("/notification/{notification_id}")
    public void markAsRed(@PathVariable("notification_id") Notification notification, @AuthenticationPrincipal(expression = "account") Account account) {
        notificationService.markAsRed(notification);
    }
}
