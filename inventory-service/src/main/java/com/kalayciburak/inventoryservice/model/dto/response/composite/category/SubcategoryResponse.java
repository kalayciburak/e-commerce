package com.kalayciburak.inventoryservice.model.dto.response.composite.category;

public record SubcategoryResponse(
        Long subcategoryId,
        String subcategoryName,
        int subcategoryCount,
        int productCount
) {
}