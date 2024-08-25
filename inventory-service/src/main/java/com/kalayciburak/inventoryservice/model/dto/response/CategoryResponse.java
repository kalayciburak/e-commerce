package com.kalayciburak.inventoryservice.model.dto.response;

public record CategoryResponse(
        Long id,
        String name,
        String description,
        CategoryResponse parentCategory) {
}
