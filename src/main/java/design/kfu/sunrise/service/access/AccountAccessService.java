package design.kfu.sunrise.service.access;

import design.kfu.sunrise.domain.model.*;
import design.kfu.sunrise.domain.model.util.Notification;
import design.kfu.sunrise.domain.model.util.Review;
import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.Exc;
import design.kfu.sunrise.repository.CommentRepository;
import design.kfu.sunrise.security.TokenUser;
import design.kfu.sunrise.service.AccountService;
import design.kfu.sunrise.service.AuthorityService;
import design.kfu.sunrise.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
@Component(value = "access")
@RequiredArgsConstructor
public class AccountAccessService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Transactional
    public boolean hasAccessToWriteComment(Account account, Club club) {
        log.info("Account with id {} trying to write comment to Club with id {}", account.getId(), club.getId());

        Authority authority = authorityService.findOrThrow(account, club);
        Set<Authority.AuthorityType> authorityTypeList = authority.getAuthorityTypes();
        boolean access = authorityTypeList
                .stream()
                .anyMatch(type -> type.equals(Authority.AuthorityType.WRITE_CLUB_COMMENTS));

        return access;
    }


    @Transactional
    public boolean hasAccessToReadComment(Account account, Club club) {
        log.info("Account with id {} trying to read comments from Club with id {}", account.getId(), club.getId());

        Authority authority = authorityService.findOrThrow(account, club);
        Set<Authority.AuthorityType> authorityTypeList = authority.getAuthorityTypes();
        boolean access = authorityTypeList
                .stream()
                .anyMatch(type -> type.equals(Authority.AuthorityType.READ_CLUB_COMMENTS));

        return access;
    }

    @Transactional
    public boolean hasAccessToCreateCategory(Account account) {
        TokenUser tokenUser=SecurityUtils.getUser().orElseThrow(Exc.sup(ErrorType.UNEXPECTED_ERROR));
        log.info("account {}", SecurityUtils.getUser());
        log.info("Account with id {} trying to create category", account.getId());
        return tokenUser.getRoles().contains(Account.Role.PARTNER.name())
                || tokenUser.getRoles().contains(Account.Role.ADMIN.name());
    }


    @Transactional
    public boolean hasAccessToCreateClub() {
        log.info("invoked");
        if(SecurityUtils.getUser().isPresent()){
           return SecurityUtils.getUser().get().getRoles().contains("ROLE_ADMIN");
        }
        return false;
    }

    @Transactional
    public boolean hasAccessToEditComment(Comment comment, Account account) {
        return comment.getAccount() == account;
    }

    @Transactional
    public boolean hasAccessToEnterClub(Account account, Club club) {
        log.info("invoked");
        //ToDo реализовать
        return true;
    }

    public boolean hasAccessToDeleteCategory(Account account, Category category) {
        TokenUser tokenUser=SecurityUtils.getUser().orElseThrow(Exc.sup(ErrorType.UNEXPECTED_ERROR));
        return tokenUser.getRoles().contains(Account.Role.ADMIN);
    }

    public boolean hasAccessToEditClub(Account account, Club club) {
        TokenUser tokenUser=SecurityUtils.getUser().orElseThrow(Exc.sup(ErrorType.UNEXPECTED_ERROR));
        return tokenUser.getRoles().contains(Account.Role.PARTNER.name())
                || tokenUser.getRoles().contains(Account.Role.ADMIN.name())
                || club.getAuthor().equals(account);
    }

    public boolean hasAccessToReadNotification(Account account, Notification notification) {
        return notification.getAccountId().equals(account.getId());
    }

    public boolean hasAccessToMakeReview(Account account, Review review, String object) {
        TokenUser tokenUser=SecurityUtils.getUser().orElseThrow(Exc.sup(ErrorType.UNEXPECTED_ERROR));
        return tokenUser.getRoles().contains(Account.Role.PARTNER.name())
                || tokenUser.getRoles().contains(Account.Role.ADMIN.name());
    }
}
