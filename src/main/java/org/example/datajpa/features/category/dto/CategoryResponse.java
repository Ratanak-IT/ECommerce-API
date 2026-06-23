package org.example.datajpa.features.category.dto;

import lombok.Builder;
import org.example.datajpa.features.category.Category;

@Builder
public record CategoryResponse(
        Integer id,
        String name,
        String description,
        String icon,
        Boolean isDelete,
        Category parentCategory
) {
}
