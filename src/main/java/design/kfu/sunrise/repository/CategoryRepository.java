package design.kfu.sunrise.repository;

import design.kfu.sunrise.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findCategoriesByParentId(Long parentId, Pageable pageable);
    Page<Category> findCategoriesByParentIsNull(Pageable pageable);
}
