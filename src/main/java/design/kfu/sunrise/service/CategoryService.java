package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.CategoryDTO;
import design.kfu.sunrise.domain.model.Category;

import java.util.Set;

public interface CategoryService {
    Category addCategory(CategoryDTO categoryDTO);
    void deleteCategory(Category category);
    Category findOrNull(Long categoryId);
    Category findOrThrow(Long categoryId);
    Category save(Category category);
    Set<Category> findByParentId(Long parentId);
}
