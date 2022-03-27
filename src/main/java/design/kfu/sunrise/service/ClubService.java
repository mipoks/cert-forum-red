package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.ClubCDTO;
import design.kfu.sunrise.domain.dto.ClubVDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.Comment;

import java.util.Set;

public interface ClubService {
    ClubVDTO addClub(ClubCDTO clubDTO);

    ClubVDTO getClub(Long clubId);
    Club findOrThrow(Long clubId);

    Set<Comment> updateComments(Club club);

    Set<Account> addAccountToClub(Club club, Account account);
}
