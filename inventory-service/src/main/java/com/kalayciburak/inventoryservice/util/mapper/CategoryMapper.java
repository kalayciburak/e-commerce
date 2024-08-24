package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.commonjpapackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.request.CategoryRequest;
import com.kalayciburak.inventoryservice.model.dto.response.CategoryResponse;
import com.kalayciburak.inventoryservice.model.dto.response.composite.CategoryWithProductsResponse;
import com.kalayciburak.inventoryservice.model.entitiy.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface CategoryMapper extends BaseMapper<CategoryResponse, Category> {
    CategoryWithProductsResponse toResponseWithProducts(Category category);

    CategoryResponse toResponse(Category category);

    Category toEntity(CategoryResponse response);

    Category toEntity(CategoryRequest request);

    void updateEntity(CategoryRequest request, @MappingTarget Category category);
}
