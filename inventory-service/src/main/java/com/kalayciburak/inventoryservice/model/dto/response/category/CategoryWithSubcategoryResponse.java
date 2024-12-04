package com.kalayciburak.inventoryservice.model.dto.response.category;

import com.kalayciburak.commonpackage.model.Sizeable;

import java.util.List;

public record CategoryWithSubcategoryResponse(
        Long id,
        String name,
        int subcategoryCount,
        List<SubcategoryResponse> subcategories
) implements Sizeable {
    @Override
    public int size() {
        return subcategories.size();
    }
}