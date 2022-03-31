package design.kfu.sunrise.domain.dto;

import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.service.StaticService;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long id;
    @NotNull
    @Size(min = 4, max = 256)
    private String name;
    private String description;
    private Long parentId;

    private Set<Long> childs = new HashSet<>();

    public static CategoryDTO from(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .parentId(category.getId())
                .childs(category.getChilds().stream().map(Category::getId).collect(Collectors.toSet()))
                .build();
    }

    public static Category toCategory(CategoryDTO categoryDTO) {
        return Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .parent(categoryDTO.getParentId() == null ? null : StaticService.getCategoryService().findOrNull(categoryDTO.getParentId()))
                .build();
    }
}
