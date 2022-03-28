package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.ClubCDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.Comment;
import design.kfu.sunrise.util.model.Filter;

import java.util.Set;

public interface ClubService {
    Club addClub(ClubCDTO clubDTO);

    Club findOrNull(Long clubId);

    Club findOrThrow(Long clubId);

    Set<Comment> updateComments(Club club);

    Set<Account> addAccountToClub(Club club, Account account);

    void saveAndFlush(Club club);

    Club moveClub(Club club, Category category);

    Club deactivateClub(Club club);

    Set<Club> findClubs(Filter filter);

    Set<Club> findAllByCreator(Account account);
}
