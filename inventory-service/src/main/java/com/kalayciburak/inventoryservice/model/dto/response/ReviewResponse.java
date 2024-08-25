package com.kalayciburak.inventoryservice.model.dto.response;

public record ReviewResponse(
        Long id,
        Long productId,
        String userId,
        Integer rating,
        String comment
) {
}
