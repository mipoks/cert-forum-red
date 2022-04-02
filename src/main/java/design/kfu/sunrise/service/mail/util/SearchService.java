package design.kfu.sunrise.service.mail.util;

import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.domain.model.Club;

import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
public interface SearchService {
    Set<Club> getClubsByName(String like);
    Set<Category> getClubsByNameAndDescription(String like);

    Set<Category> getCategoriesByName(String like);
    Set<Category> getCategoriesByNameAndDescription(String like);

    void saveCategory(Category category);
    void saveClub(Club club);
}
