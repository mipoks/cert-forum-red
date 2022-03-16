package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.ClubDTO;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.Exc;
import design.kfu.sunrise.repository.ClubRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Override
    public Club saveClub(ClubDTO clubDTO) {
        return clubRepository.save(ClubDTO.toClub(clubDTO));
    }

    @Override
    public ClubDTO getClub(Long clubId) {
        Club club = findOrThrow(clubId);
        return ClubDTO.fromClub(club);
    }

    @Override
    public Club findOrThrow(Long clubId) {
        log.info("im here");
        return clubRepository
                .findById(clubId)
                .orElseThrow(Exc.sup(ErrorType.ENTITY_NOT_FOUND, "Сущность клуба не найдена"));
    }
}
