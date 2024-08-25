package com.kalayciburak.inventoryservice.model.dto.response;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        // TODO: Image, Review, Attribute response eklemeleri yapılacak
        CategoryResponse category) {
}
