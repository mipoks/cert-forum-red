package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Authority;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Component(value = "access")
@RequiredArgsConstructor
public class AccessService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AccountService accountService;


    @Transactional
    public boolean hasAccessToWriteComment(Account account, Club club) {
        log.info("Account with id {} trying to write comment to Club with id {}", account.getId(), club.getId());
        List<Authority> authorityList = accountService.findOrThrow(account.getId())
                .getAuthorities();
        Authority writeAuthority = null;
        for (Authority authority : authorityList) {

            if (authority.getAuthorityType().equals(Authority.AuthorityType.WRITE_CLUB_COMMENTS)) {
                writeAuthority = authority;
            }
        }
        boolean access = false;
        if (writeAuthority != null) {
            access = writeAuthority.getClubs()
                    .stream()
                    .filter(sub -> sub.equals(club))
                    .findFirst()
                    .isPresent();
        }
        return access;
    }


    @Transactional
    public boolean hasAccessToReadComment(Account account, Club club) {
        log.info("Account with id {} trying to read comments from Club with id {}", account.getId(), club.getId());
        List<Authority> authorityList = accountService.findOrThrow(account.getId())
                .getAuthorities();
        Authority writeAuthority = null;
        for (Authority authority : authorityList) {

            if (authority.getAuthorityType().equals(Authority.AuthorityType.READ_CLUB_COMMENTS)) {
                writeAuthority = authority;
            }
        }
        boolean access = false;
        if (writeAuthority != null) {
            access = writeAuthority.getClubs()
                    .stream()
                    .filter(sub -> sub.equals(club))
                    .findFirst()
                    .isPresent();
        }
        return access;
    }


    @Transactional
    public boolean hasAccessToCreateClub(Account account, Club club) {
        //ToDo реализовать последним
        return true;
    }



}
