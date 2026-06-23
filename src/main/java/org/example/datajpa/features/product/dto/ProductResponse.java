package org.example.datajpa.features.product.dto;

import org.example.datajpa.features.category.dto.CategorySnippetResponse;

import java.math.BigDecimal;

public record ProductResponse(
        String code,
        String slug,
        String name,
        String description,
        String thumbnail,
        BigDecimal unitPrice,
        Integer qty,
        Boolean isAvailable,
        Boolean isDelete,
        CategorySnippetResponse category
) {
}
