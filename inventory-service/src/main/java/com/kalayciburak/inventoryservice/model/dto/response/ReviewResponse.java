package com.kalayciburak.inventoryservice.model.dto.response;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long id,
        String userId,
        Long productId,
        Integer rating,
        String comment,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
