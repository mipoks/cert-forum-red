package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.CategoryDTO;
import design.kfu.sunrise.domain.model.Category;

public interface CategoryService {
    Category addCategory(CategoryDTO categoryDTO);
    void deleteCategory(Category category);
}
