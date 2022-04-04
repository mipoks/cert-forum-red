package design.kfu.sunrise.service.access;

import design.kfu.sunrise.domain.model.*;
import design.kfu.sunrise.repository.CommentRepository;
import design.kfu.sunrise.service.AccountService;
import design.kfu.sunrise.service.AuthorityService;
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
        log.info("Account with id {} trying to create category", account.getId());
        return account.getRole().equals(Account.Role.PARTNER) || account.getRole().equals(Account.Role.ADMIN);
    }


    @Transactional
    public boolean hasAccessToCreateClub(Account account) {
        //ToDo реализовать
        return true;
    }

    @Transactional
    public boolean hasAccessToEditComment(Comment comment, Account account) {
        if (comment.getAccount() == account) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean hasAccessToEnterClub(Account account, Club club) {
        //ToDo реализовать
        return true;
    }

    public boolean hasAccessToDeleteCategory(Account account, Category category) {
        return account.getRole().equals(Account.Role.ADMIN);
    }

    public boolean hasAccessToUpdateClub(Account account, Club club) {
        //ToDo реализовать
        return true;
    }
}
