package design.kfu.sunrise.repository;

import design.kfu.sunrise.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Set<Category> findCategoriesByParentId(Long parentId);
    Set<Category> findCategoriesByParentIsNull();
}
