package org.example.datajpa.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.datajpa.domain.Category;

public record CategoryRequest(
        @NotBlank(message = "Category name is required")
        @Size(min = 3, max = 100, message = "Category name must be between 3 and 100 characters")
        String name,
        @Size(max = 500, message = "Description cannot exceed 500 characters")
        String description,
        @Size(max = 50, message = "Icon must not exceed 50 characters")
        String icon,
        @Min(value = 1, message = "Parent category id must be greater than 0")
        Integer parentCategoryId
) {
}
