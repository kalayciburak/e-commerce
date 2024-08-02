package com.kalayciburak.inventoryservice.model.dto.response;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        Long categoryId,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String imageUrl,
        String categoryName) {
}
