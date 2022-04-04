package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.category.CategoryDTO;
import design.kfu.sunrise.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Category addCategory(CategoryDTO categoryDTO);
    void deleteCategory(Category category);
    Category findOrNull(Long categoryId);
    Category findOrThrow(Long categoryId);
    Category save(Category category);
    Page<Category> findByParentId(Long parentId, Pageable pageable);
}
