package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.CategoryDTO;
import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(CategoryDTO categoryDTO) {
        Category category = categoryDTO.toCategory(categoryDTO);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public Category findOrNull(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }
}