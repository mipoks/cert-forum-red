package design.kfu.sunrise.controller.util;

import design.kfu.sunrise.service.mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author Daniyar Zakiev
 */
@RestController
@RequestMapping("v1")
public class EmailConfirmationController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/account/email")
    public String confirmEmail(@RequestParam @NotNull String code) {
        return emailService.confirmEmail(code);
    }
}
