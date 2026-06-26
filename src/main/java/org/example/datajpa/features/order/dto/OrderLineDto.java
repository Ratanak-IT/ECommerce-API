package org.example.datajpa.features.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record OrderLineDto(
        @NotBlank(message = "Code is required")
        String code,
        @Positive(message = "Qty must be positive")
        @NotNull(message = "Qty is required")
        Integer qty,
        @NotNull(message = "Unit price is required")
        @Positive(message = "Unit price must be positive")
        Double unitPrice
) {
}
