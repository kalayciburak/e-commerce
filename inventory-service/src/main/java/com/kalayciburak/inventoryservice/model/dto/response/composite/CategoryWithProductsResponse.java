package com.kalayciburak.inventoryservice.model.dto.response.composite;

import com.kalayciburak.inventoryservice.model.dto.response.ProductResponse;

import java.util.Set;

public record CategoryWithProductsResponse(
        Long id,
        String name,
        String description,
        Set<ProductResponse> products) {
}
