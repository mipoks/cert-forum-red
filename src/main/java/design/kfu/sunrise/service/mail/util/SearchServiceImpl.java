package design.kfu.sunrise.service.mail.util;

import design.kfu.sunrise.domain.dto.category.CategoryDTO;
import design.kfu.sunrise.domain.dto.club.ClubVDTO;
import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.domain.model.Club;
import design.kfu.sunrise.esrepository.elastic.ESCategoryRepository;
import design.kfu.sunrise.esrepository.elastic.ESClubRepository;
import design.kfu.sunrise.util.model.ModelEvent;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ESCategoryRepository esCategoryRepository;

    @Autowired
    private ESClubRepository esClubRepository;

    @Override
    public Set<ClubVDTO> getClubsByName(String like) {
        return esClubRepository.findAllByNameContaining(like);
    }

    @Override
    public Set<ClubVDTO> getClubsByNameAndDescription(String like) {
        Set<ClubVDTO> allByDescriptionContaining = esClubRepository.findAllByDescriptionContaining(like);
        allByDescriptionContaining
                .addAll(esClubRepository.findAllByNameContaining(like));
        return allByDescriptionContaining;
    }

    @Override
    public Set<CategoryDTO> getCategoriesByName(String like) {
        Set<CategoryDTO> allByName = esCategoryRepository.findAllByNameContaining(like);
        return allByName;
    }

    @Override
    public Set<CategoryDTO> getCategoriesByNameAndDescription(String like) {
        Set<CategoryDTO> allByDescriptionContaining = esCategoryRepository.findAllByDescriptionContaining(like);
        allByDescriptionContaining
                .addAll(esCategoryRepository.findAllByNameContaining(like));
        return allByDescriptionContaining;
    }

    @Override
    public void saveCategory(CategoryDTO category) {
        esCategoryRepository.save(category);
    }

    @Override
    public void saveClub(ClubVDTO club) {
        esClubRepository.save(club);
    }

    //ToDo переделать
    @EventListener
    public void handleCategoryEvent(ModelEvent<Object> modelEvent) {
        if (modelEvent.getModel().getClass().isInstance(Category.class)) {
            Category category = (Category) modelEvent.getModel();
            switch (modelEvent.getEvent()) {
                case "save", "update" -> esCategoryRepository.save(CategoryDTO.from(category));
                case "remove" -> esCategoryRepository.delete(CategoryDTO.from(category));
            }
        }
    }

    @EventListener
    public void handleClubEvent(ModelEvent<Object> modelEvent) {
        if (modelEvent.getModel().getClass().isInstance(Club.class)) {
            Club club = (Club) modelEvent.getModel();
            switch (modelEvent.getEvent()) {
                case "save", "update" -> esClubRepository.save(ClubVDTO.from(club));
                case "remove" -> esClubRepository.delete(ClubVDTO.from(club));
            }
        }
    }


}
