package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.ClubDTO;
import design.kfu.sunrise.domain.dto.ClubVDTO;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.domain.model.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClubService {
    @Transactional
    ClubVDTO addClub(ClubDTO clubDTO);

    @Transactional
    ClubVDTO getClub(Long clubId);
    Club findOrThrow(Long clubId);

    List<Comment> updateComments(Club club);
}
