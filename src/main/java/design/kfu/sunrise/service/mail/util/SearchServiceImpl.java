package design.kfu.sunrise.service.mail.util;

import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.domain.model.Club;
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
    public Set<Club> getClubsByName(String like) {
        return null;
    }

    @Override
    public Set<Category> getClubsByNameAndDescription(String like) {
        return null;
    }

    @Override
    public Set<Category> getCategoriesByName(String like) {
        Set<Category> allByName = esCategoryRepository.findAllByNameContaining(like);
        return allByName;
    }

    @Override
    public Set<Category> getCategoriesByNameAndDescription(String like) {
        return null;
    }

    @Override
    public void saveCategory(Category category) {
        esCategoryRepository.save(category);
    }

    @Override
    public void saveClub(Club club) {

    }


}
