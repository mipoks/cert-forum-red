package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.club.ClubCDTO;
import design.kfu.sunrise.domain.model.*;
import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.Exc;
import design.kfu.sunrise.repository.AccountRepository;
import design.kfu.sunrise.repository.ClubRepository;
import design.kfu.sunrise.util.model.Filter;
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
    public Club addClub(ClubCDTO clubDTO) {
        return clubRepository.save(ClubCDTO.toClub(clubDTO));
    }

    @Override
    public Club findOrNull(Long clubId) {
        return clubRepository.findById(clubId).orElse(null);
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
        account.addClub(club);
        clubRepository.saveAndFlush(club);

        Authority authority = authorityService.findOrThrow(account, club);
        authority
                .addAuthotityType(Authority.AuthorityType.READ_CLUB_COMMENTS)
                .addAuthotityType(Authority.AuthorityType.WRITE_CLUB_COMMENTS);

        authorityService.save(authority);
        return club.getAccounts();
    }

    @Override
    public void saveAndFlush(Club club) {
        clubRepository.saveAndFlush(club);
    }

    @Override
    public Club moveClub(Club club, Category category) {
        club.setCategory(category);
        clubRepository.save(club);
        return club;
    }

    @Override
    public Club deactivateClub(Club club) {
        club.getActiveInfo().setExpired(true);
        clubRepository.save(club);
        return club;
    }

    @Override
    public Set<Club> findClubs(Filter filter) {
        //ToDo реализовать поиск с фильтрами
        return null;
    }


    @Override
    public Set<Club> findAllByAuthor(Account account) {
        return clubRepository.findAllByAuthor(account);
    }


}
