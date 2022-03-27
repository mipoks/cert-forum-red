package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.ClubCDTO;
import design.kfu.sunrise.domain.dto.ClubVDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Authority;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.Comment;
import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.Exc;
import design.kfu.sunrise.repository.AccountRepository;
import design.kfu.sunrise.repository.ClubRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private AccountService accountService;

    @Override
    public ClubVDTO addClub(ClubCDTO clubDTO) {
        return ClubVDTO.fromClub(clubRepository.save(ClubCDTO.toClub(clubDTO)));
    }

    @Override
    public ClubVDTO getClub(Long clubId) {
        Club club = findOrThrow(clubId);
        return ClubVDTO.fromClub(club);
    }

    @Override
    public Club findOrThrow(Long clubId) {
        return clubRepository
                .findById(clubId)
                .orElseThrow(Exc.sup(ErrorType.ENTITY_NOT_FOUND, "Сущность клуба не найдена"));
    }

    @Override
    public Set<Comment> updateComments(Club club) {
        clubRepository.save(club);
        return club.getComments();
    }

    @Autowired
    private AccountRepository accountRepository;


    @Override
    @Transactional
    public Set<Account> addAccountToClub(Club club, Account detachedAccount) {

        Account account = accountRepository.findById(detachedAccount.getId()).get();

        Authority authority = new Authority();
        authority.setAuthorityType(Authority.AuthorityType.READ_CLUB_COMMENTS);
        account.getAuthorities().add(authority);
        club.getAuthorities().add(authority);
        club.getAccounts().add(account);
        clubRepository.save(club);
        authority.getAccounts().add(account);
        authorityService.addAuthority(authority);
//        clubRepository.save(club);
//        accountService.update(account);
//
        return club.getAccounts();
    }
}
