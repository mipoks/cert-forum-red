package design.kfu.sunrise.service.mail.util;

import design.kfu.sunrise.domain.dto.CategoryDTO;
import design.kfu.sunrise.domain.dto.ClubVDTO;

import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
public interface SearchService {
    Set<ClubVDTO> getClubsByName(String like);
    Set<ClubVDTO> getClubsByNameAndDescription(String like);

    Set<CategoryDTO> getCategoriesByName(String like);
    Set<CategoryDTO> getCategoriesByNameAndDescription(String like);

    void saveCategory(CategoryDTO category);
    void saveClub(ClubVDTO club);
}
