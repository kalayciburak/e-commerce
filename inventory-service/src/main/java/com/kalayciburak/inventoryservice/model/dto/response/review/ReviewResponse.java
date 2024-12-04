package com.kalayciburak.inventoryservice.model.dto.response.review;

public record ReviewResponse(
        Long id,
        String userId,
        Long productId,
        Integer rating,
        String comment
) {
}
