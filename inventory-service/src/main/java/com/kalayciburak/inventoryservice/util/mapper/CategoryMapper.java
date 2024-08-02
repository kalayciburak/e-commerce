package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.commonjpapackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.response.CategoryResponse;
import com.kalayciburak.inventoryservice.model.dto.response.composite.CategoryWithProductsResponse;
import com.kalayciburak.inventoryservice.model.entitiy.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface CategoryMapper extends BaseMapper<CategoryResponse, Category> {
    CategoryWithProductsResponse toResponseWithProducts(Category category);
}
