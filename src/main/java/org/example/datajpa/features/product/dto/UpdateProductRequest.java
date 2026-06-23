package org.example.datajpa.features.product.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record UpdateProductRequest(
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
        Boolean isAvailable
) {
}
