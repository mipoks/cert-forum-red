package design.kfu.sunrise.service.mail.util;

import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.domain.model.Club;

import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
public interface SearchService {
    Set<Club> getClubsLike(String like);
    Set<Category> getCategoryLike(String like);
}
