package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.dto.category.CategoryDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Daniyar Zakiev
 */
@Slf4j
@RestController
@RequestMapping(value = "v1")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("@access.hasAccessToCreateCategory(#account)")
    @PostMapping("/category")
    public CategoryDTO addCategory(@Valid @RequestBody CategoryDTO categoryDTO, @AuthenticationPrincipal(expression = "account") Account account){
        Category category = categoryService.addCategory(categoryDTO);
        return CategoryDTO.from(category);
    }


    @PreAuthorize("@access.hasAccessToDeleteCategory(#account, #category)")
    @PostMapping("/category/{category_id}/delete")
    public Boolean deleteCategory(@PathVariable("category_id") Category category, @AuthenticationPrincipal(expression = "account") Account account){
        categoryService.deleteCategory(category);
        return true;
    }

    @PreAuthorize("@access.hasAccessToCreateCategory(#account)")
    @PutMapping("/category/{category_id}")
    public CategoryDTO updateCategory(@PathVariable("category_id") Category category, @Valid @RequestBody CategoryDTO categoryDTO, @AuthenticationPrincipal(expression = "account") Account account){
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        categoryService.save(category);
        return CategoryDTO.from(category);
    }

    @GetMapping("/categories")
    public Set<CategoryDTO> getCategories(@Nullable @RequestParam("parent_id") Long parentId) {
        return categoryService.findByParentId(parentId)
                .stream()
                .map(CategoryDTO::from)
                .collect(Collectors.toSet());
    }
}
