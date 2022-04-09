package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.category.CategoryDTO;
import design.kfu.sunrise.domain.dto.club.ClubVDTO;
import design.kfu.sunrise.domain.event.AccountEvent;
import design.kfu.sunrise.domain.event.CategoryEvent;
import design.kfu.sunrise.domain.event.ClubEvent;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.util.ActivationCode;
import design.kfu.sunrise.domain.model.util.Notification;
import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.RestException;
import design.kfu.sunrise.service.mail.EmailService;
import design.kfu.sunrise.service.mail.context.AbstractEmailContext;
import design.kfu.sunrise.service.mail.util.ActivationCodeService;
import design.kfu.sunrise.service.mail.util.EmailContextGenerator;
import design.kfu.sunrise.service.mail.util.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.Instant;

/**
 * @author Daniyar Zakiev
 */
@Service
@Slf4j
public class DomainEventListener {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ActivationCodeService activationCodeService;

    @Autowired
    private EmailContextGenerator emailContextGenerator;

    @EventListener
    public void handleAccountEventCreate(AccountEvent event) {
        if (event.getEvent().equals(AccountEvent.Event.CREATE.getName())) {
            Account account = (Account) event.getObject();
            ActivationCode activationCode = activationCodeService.generate(account);
            activationCodeService.save(activationCode);

            AbstractEmailContext abstractEmailContext = emailContextGenerator.generateConfirmationContext(account, activationCode);
            try {
                emailService.sendEmail(abstractEmailContext);
            } catch (MessagingException e) {
                log.error(e.getMessage());
                throw new RestException(ErrorType.UNEXPECTED_ERROR, "Не удалось отправить письмо с кодом подтверждения");
            }
        }
    }

    @Autowired
    private SearchService searchService;

    @EventListener
    public void handleCategoryEventSaveUpdate(CategoryEvent event) {
        if (event.getEvent().equals(CategoryEvent.Event.SAVE.getName())
                || event.getEvent().equals(CategoryEvent.Event.UPDATE.getName())) {
            Category category = (Category) event.getObject();
            searchService.saveCategory(CategoryDTO.from(category));
        }
    }

    @EventListener
    public void handleCategoryEventDelete(CategoryEvent event) {
        if (event.getEvent().equals(CategoryEvent.Event.DELETE.getName())) {
            Category category = (Category) event.getObject();
            searchService.deleteCategory(CategoryDTO.from(category));
        }
    }

    @EventListener
    public void handleClubEventSave(ClubEvent event) {
        if (event.getEvent().equals(ClubEvent.Event.SAVE.getName()) || event.getEvent().equals(CategoryEvent.Event.UPDATE.getName())) {
            Club club = (Club) event.getObject();
            searchService.saveClub(ClubVDTO.from(club));
        }
    }

    @EventListener
    public void handleClubEventDeactivate(ClubEvent event) {
        if (event.getEvent().equals(ClubEvent.Event.CLUB_DEACTIVATE.getName())) {
            Club club = (Club) event.getObject();
            searchService.deleteClub(ClubVDTO.from(club));
        }
    }

    @Autowired
    private NotificationService notificationService;

    @EventListener
    public void handleClubEventAccountEnter(ClubEvent event) {
        if (event.getEvent().equals(ClubEvent.Event.ACCOUNT_ENTER.getName())) {
            Club club = (Club) event.getObject();

            Notification notification = Notification.builder()
                    .description(ACCOUNT_TO_CLUB_ENTER)
                    .name(club.getName())
                    .instant(Instant.now())
                    .read(false)
                    .url(club.getId().toString())
                    .build();
            notificationService.notifyClub(notification, club);
            searchService.deleteClub(ClubVDTO.from(club));
        }
    }

    public static final String ACCOUNT_TO_CLUB_ENTER = "В клуб вошел новый участник";

    //CommentEvent -> отправить в клуб

    //ReviewEvent -> отправить в аккаунт

    //разобрать с ReviewEvent и CommentEvent, так как при сохранении Comment, он должен быть invisible,
    //и только после Review станет видимым

    //AccountEvent -> отправить письмо

    //CategoryEvent -> ничего

    //ClubEvent -> отправить notification в клуб

    //ClubEvent -> сохранить в ES

    //CategoryEvent -> сохранить в ES


}
