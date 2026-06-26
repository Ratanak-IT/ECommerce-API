package org.example.datajpa.features.order.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public record OrderRequest(
        @NotBlank(message = "Address is required")
        String address,
        @NotNull(message = "Discount is required")
        @Min(0)
        @Max(100)
        Float discount,
        @Size(max = 255)
        String remark,
        @NotEmpty(message = "Order line is required")
        List<OrderLineDto> orderLine
) {
}
