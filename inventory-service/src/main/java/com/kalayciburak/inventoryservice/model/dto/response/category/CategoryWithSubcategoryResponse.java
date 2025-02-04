package com.kalayciburak.inventoryservice.model.dto.response.category;

import com.kalayciburak.commonpackage.core.response.common.Sizable;

import java.util.List;

public record CategoryWithSubcategoryResponse(
        Long id,
        String name,
        int subcategoryCount,
        List<SubcategoryResponse> subcategories
) implements Sizable {
    @Override
    public int size() {
        return subcategories.size();
    }
}