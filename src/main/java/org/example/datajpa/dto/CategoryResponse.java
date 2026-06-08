package org.example.datajpa.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import org.example.datajpa.domain.Category;

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
