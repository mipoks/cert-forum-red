package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.ClubDTO;
import design.kfu.sunrise.domain.model.Club;
import org.springframework.transaction.annotation.Transactional;

public interface ClubService {
    @Transactional
    Club saveClub(ClubDTO clubDTO);

    @Transactional
    ClubDTO getClub(Long clubId);
    Club findOrThrow(Long clubId);

}
