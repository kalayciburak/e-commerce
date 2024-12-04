package com.kalayciburak.inventoryservice.model.dto.response.product;

import com.kalayciburak.inventoryservice.model.dto.response.category.CategoryResponse;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        CategoryResponse category,
        List<ProductImageResponse> images,
        List<ProductAttributeResponse> attributes,
        List<ProductReviewResponse> reviews
) {
}
