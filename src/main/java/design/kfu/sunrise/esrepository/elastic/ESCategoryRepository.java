package design.kfu.sunrise.esrepository.elastic;

import design.kfu.sunrise.domain.model.Category;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
@Repository
public interface ESCategoryRepository extends ElasticsearchRepository<Category, Long> {
    Set<Category> findAllByNameContaining(String like);
    Set<Category> findAllByDescriptionContaining(String like);
}
