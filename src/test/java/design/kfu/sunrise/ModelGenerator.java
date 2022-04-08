package design.kfu.sunrise;

import design.kfu.sunrise.domain.dto.category.CategoryDTO;
import design.kfu.sunrise.domain.model.Category;

import java.util.UUID;

public class ModelGenerator {
    public static Category generateCategory() {
        CategoryDTO dto = CategoryDTO.builder()
                .description(generateString())
                .name(generateString())
                .build();
        Category category = CategoryDTO.toCategory(dto);
        return category;
    }

    public static CategoryDTO generateCategoryDTO() {
        CategoryDTO dto = CategoryDTO.builder()
                .description(generateString())
                .name(generateString())
                .build();
        return dto;
    }

    public static String generateString() {
        return UUID.randomUUID().toString();
    }
}
