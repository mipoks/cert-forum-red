package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.club.ClubCDTO;
import design.kfu.sunrise.domain.event.ClubEvent;
import design.kfu.sunrise.domain.model.*;
import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.Exc;
import design.kfu.sunrise.repository.AccountRepository;
import design.kfu.sunrise.repository.ClubRepository;
import design.kfu.sunrise.util.model.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public Club addClub(ClubCDTO clubDTO) {
        Club saved = clubRepository.save(ClubCDTO.toClub(clubDTO));
        publisher.publishEvent(new ClubEvent(Club.class.getName(), ClubEvent.Event.SAVE.getName(), saved));
        //ToDo убрать след строку после сдачи проекта:
        publisher.publishEvent(new ClubEvent(Club.class.getName(), ClubEvent.Event.PUBLISH.getName(), saved));
        return saved;
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
        Club saved = clubRepository.save(club);
        //Не перехватывается
        publisher.publishEvent(new ClubEvent(Club.class.getName(), ClubEvent.Event.COMMENT_UPDATE.getName(), saved));
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

        publisher.publishEvent(new ClubEvent(Club.class.getName(), ClubEvent.Event.ACCOUNT_ENTER.getName(), club));

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
        Club saved = clubRepository.save(club);
        publisher.publishEvent(new ClubEvent(Club.class.getName(), ClubEvent.Event.CLUB_MOVE.getName(), saved));
        return saved;
    }

    @Override
    public Club deactivateClub(Club club) {
        club.getClubInfo().setExpired(true);
        Club saved = clubRepository.save(club);
        publisher.publishEvent(new ClubEvent(Club.class.getName(), ClubEvent.Event.CLUB_DEACTIVATE.getName(), saved));
        return saved;
    }

    @Override
    public Set<Club> findClubs(Filter filter) {
        Club club = Club.builder().build();
        if (filter.getCategoryId() != null) {
            Category category = categoryService.findOrThrow(filter.getCategoryId());
            club.setCategory(category);
        }
        Example<Club> example = Example.of(club);
        return new HashSet<>(clubRepository.findAll(example));
    }


    @Override
    public Set<Club> findAllByAuthor(Account account) {
        return clubRepository.findAllByAuthor(account);
    }

    @Override
    public Club updateClub(Club club) {
        club.getClubInfo().setVisible(false);
        Club saved = clubRepository.save(club);
        publisher.publishEvent(new ClubEvent(Club.class.getName(), ClubEvent.Event.UPDATE.getName(), saved));
        return saved;
    }


}
