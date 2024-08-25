package com.kalayciburak.inventoryservice.model.dto.response.composite.category;

import com.kalayciburak.commonpackage.model.Sizeable;

import java.util.List;

public record AllParentCategoriesResponse(List<CategoryWithSubcategoryResponse> categories) implements Sizeable {
    @Override
    public int size() {
        return categories.size();
    }
}