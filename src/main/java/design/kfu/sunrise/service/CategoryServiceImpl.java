package design.kfu.sunrise.service;

import design.kfu.sunrise.domain.dto.category.CategoryDTO;
import design.kfu.sunrise.domain.event.CategoryEvent;
import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.exception.ErrorType;
import design.kfu.sunrise.exception.Exc;
import design.kfu.sunrise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    @Override
    public Category addCategory(CategoryDTO categoryDTO) {
        Category category = CategoryDTO.toCategory(categoryDTO);
        Category saved = categoryRepository.save(category);
        publisher.publishEvent(new CategoryEvent(Category.class.getName(), CategoryEvent.Event.SAVE.getName(), saved));
        return saved;
    }

    @Override
    public void deleteCategory(Category category) {
        publisher.publishEvent(new CategoryEvent(Category.class.getName(), CategoryEvent.Event.DELETE.getName(), category));
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
    public Category update(Category category) {
        Category saved = categoryRepository.save(category);
        publisher.publishEvent(new CategoryEvent(Category.class.getName(), CategoryEvent.Event.UPDATE.getName(), saved));
        return saved;
    }

    @Override
    public Page<Category> findByParentId(Long parentId, Pageable pageable) {
        if (parentId == null) {
            return categoryRepository.findCategoriesByParentIsNull(pageable);
        } else {
            return categoryRepository.findCategoriesByParentId(parentId, pageable);
        }
    }
}
