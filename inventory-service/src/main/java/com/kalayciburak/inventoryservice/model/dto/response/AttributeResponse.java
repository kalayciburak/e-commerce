package com.kalayciburak.inventoryservice.model.dto.response;

public record AttributeResponse(
        Long id,
        Long productId,
        String name,
        String value
) {
}
