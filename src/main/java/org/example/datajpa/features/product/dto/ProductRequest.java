package org.example.datajpa.features.product.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank
        String name,
        @Size(max = 500)
        String description,
        @NotBlank
        String thumbnail,
        @NotNull
        @Positive
        BigDecimal unitPrice,
        @NotNull
        @Min(0)
        Integer qty,
        @NotNull
        Boolean isAvailable,
        @NotNull(message = "Category is required")
        @Positive(message = "Category id must be greater than 0")
        Integer categoryId
) {
}
