package com.kalayciburak.inventoryservice.model.dto.response.product;

import com.kalayciburak.inventoryservice.model.dto.response.category.CategoryResponse;

import java.math.BigDecimal;

public record ProductSimpleResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        CategoryResponse category
) {
}