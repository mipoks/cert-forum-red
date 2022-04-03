package design.kfu.sunrise.service.mail.util;

import design.kfu.sunrise.domain.dto.CategoryDTO;
import design.kfu.sunrise.domain.dto.ClubVDTO;
import design.kfu.sunrise.esrepository.elastic.ESCategoryRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ESCategoryRepository esCategoryRepository;

    //ToDo подключить к Redis
    @Override
    public Set<ClubVDTO> getClubsByName(String like) {
        return null;
    }

    @Override
    public Set<CategoryDTO> getClubsByNameAndDescription(String like) {
        return null;
    }

    @Override
    public Set<CategoryDTO> getCategoriesByName(String like) {
        Set<CategoryDTO> allByName = esCategoryRepository.findAllByNameContaining(like);
        return allByName;
    }

    @Override
    public Set<CategoryDTO> getCategoriesByNameAndDescription(String like) {
        return null;
    }

    @Override
    public void saveCategory(CategoryDTO category) {
        esCategoryRepository.save(category);
    }

    @Override
    public void saveClub(ClubVDTO club) {

    }


}
