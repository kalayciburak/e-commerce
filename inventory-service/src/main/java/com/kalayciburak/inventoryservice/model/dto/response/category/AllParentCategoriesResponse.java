package com.kalayciburak.inventoryservice.model.dto.response.category;

import com.kalayciburak.commonpackage.core.response.common.Sizable;

import java.util.List;

public record AllParentCategoriesResponse(List<CategoryWithSubcategoryResponse> categories) implements Sizable {
    @Override
    public int size() {
        return categories.size();
    }
}