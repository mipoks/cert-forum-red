package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.util.Notification;
import design.kfu.sunrise.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/v1/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public Set<Notification> getNotifications(@AuthenticationPrincipal(expression = "account") Account account) {
        return notificationService.findByAccount(account);
    }

    @PreAuthorize("@access.hasAccessToReadNotification(#account, #notification)")
    @PutMapping("/{notification_id}")
    public void markAsRed(@PathVariable("notification_id") Notification notification, @AuthenticationPrincipal(expression = "account") Account account) {
        notificationService.markAsRed(notification);
    }
}
