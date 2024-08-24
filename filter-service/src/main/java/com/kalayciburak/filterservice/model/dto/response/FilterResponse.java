package com.kalayciburak.filterservice.model.dto.response;

import java.math.BigDecimal;

public record FilterResponse(
        String id,
        Long productId,
        Long categoryId,
        String name,
        String description,
        BigDecimal price,
        int stock,
        String imageUrl,
        String categoryName,
        String categoryDescription) {
}
