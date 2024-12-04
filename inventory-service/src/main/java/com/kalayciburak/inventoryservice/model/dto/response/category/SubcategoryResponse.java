package com.kalayciburak.inventoryservice.model.dto.response.category;

public record SubcategoryResponse(
        Long subcategoryId,
        String subcategoryName,
        int subcategoryCount,
        int productCount
) {
}