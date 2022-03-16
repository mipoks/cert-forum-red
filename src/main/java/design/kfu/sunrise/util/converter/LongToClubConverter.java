package design.kfu.sunrise.util.converter;

import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.service.ClubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Daniyar Zakiev
 */
@Component
@Slf4j
public class LongToClubConverter implements Converter<Long, Club> {

    @Autowired
    private ClubService clubService;

    @Override
    public Club convert(Long clubId) {

        log.info("ClubId is {}",clubId);
        return clubService.findOrThrow(clubId);
    }
}
