package com.kalayciburak.inventoryservice.model.dto.response.product;

public record ProductAttributeResponse(
        Long id,
        String name,
        String value
) {
}