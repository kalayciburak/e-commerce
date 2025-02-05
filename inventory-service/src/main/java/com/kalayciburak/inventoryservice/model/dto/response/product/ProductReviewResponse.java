package com.kalayciburak.inventoryservice.model.dto.response.product;

import java.time.LocalDateTime;

public record ProductReviewResponse(
        Long id,
        String userId,
        Integer rating,
        String comment,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
