package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.CategoryDTO;
import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.Exc;
import design.kfu.sunrise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ApplicationEventPublisher publisher;


    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    @Override
    public Category addCategory(CategoryDTO categoryDTO) {
        Category category = CategoryDTO.toCategory(categoryDTO);
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

    @Override
    public Category findOrThrow(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(Exc.sup(ErrorType.ENTITY_NOT_FOUND,"Сущность категории не найдена"));
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Set<Category> findByParentId(Long parentId) {
        if (parentId == null) {
            return categoryRepository.findCategoriesByParentIsNull();
        } else {
            return categoryRepository.findCategoriesByParentId(parentId);
        }
    }
}
