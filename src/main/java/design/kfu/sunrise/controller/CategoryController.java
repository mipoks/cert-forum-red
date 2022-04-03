package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.dto.CategoryDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.service.CategoryService;
import design.kfu.sunrise.service.mail.util.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Daniyar Zakiev
 */
@Slf4j
@RestController
@RequestMapping(value = "v1")
public class CategoryController {
    //ToDo реализовать поиск

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SearchService searchService;

    @PreAuthorize("@access.hasAccessToCreateCategory(#account)")
    @PostMapping("/category")
    public CategoryDTO addCategory(@Valid @RequestBody CategoryDTO categoryDTO, @AuthenticationPrincipal(expression = "account") Account account){
        Category category = categoryService.addCategory(categoryDTO);
        CategoryDTO fr = CategoryDTO.from(category);
        searchService.saveCategory(fr);
        return CategoryDTO.from(category);
    }


    @PreAuthorize("@access.hasAccessToDeleteCategory(#account, #category)")
    @PostMapping("/category/{category_id}/delete")
    public Boolean deleteCategory(@PathVariable("category_id") Category category, @AuthenticationPrincipal(expression = "account") Account account){
        categoryService.deleteCategory(category);
        return true;
    }
}
