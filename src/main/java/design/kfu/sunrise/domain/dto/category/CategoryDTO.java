package design.kfu.sunrise.domain.dto.category;

import design.kfu.sunrise.domain.model.Category;
import design.kfu.sunrise.service.StaticService;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
@Document(indexName = "categoryind")
public class CategoryDTO {

    private Long id;

    @NotNull
    @Size(min = 4, max = 256)
    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Transient
    private Long parentId;

    @Transient
    private Set<Long> childs = new HashSet<>();

    public static CategoryDTO from(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .childs(category.getChilds().stream().map(Category::getId).collect(Collectors.toSet()))
                .build();
    }

    public static CategoryDTO fromExcludeChilds(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .build();
    }

    public static Category toCategory(CategoryDTO categoryDTO) {
        return Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .childs(new HashSet<>())
                .parent(categoryDTO.getParentId() == null ? null : StaticService.getCategoryService().findOrNull(categoryDTO.getParentId()))
                .build();
    }
}
