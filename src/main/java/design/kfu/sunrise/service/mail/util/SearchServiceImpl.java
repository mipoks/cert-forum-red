package design.kfu.sunrise.service.mail.util;

import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.domain.model.Club;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
@Service
public class SearchServiceImpl implements SearchService {

    //ToDo подключить к Redis
    @Override
    public Set<Club> getClubsLike(String like) {
        return null;
    }

    @Override
    public Set<Category> getCategoryLike(String like) {
        return null;
    }
}
