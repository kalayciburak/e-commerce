package com.kalayciburak.inventoryservice.model.dto.response.product;

public record ProductReviewResponse(
        Long id,
        String userId,
        Integer rating,
        String comment
) {
}
