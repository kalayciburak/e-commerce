package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.commonpackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.request.CategoryRequest;
import com.kalayciburak.inventoryservice.model.dto.response.CategoryResponse;
import com.kalayciburak.inventoryservice.model.dto.response.composite.category.CategoryWithSubcategoryResponse;
import com.kalayciburak.inventoryservice.model.dto.response.composite.category.SubcategoryResponse;
import com.kalayciburak.inventoryservice.model.entitiy.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryResponse, Category> {
    CategoryResponse toResponse(Category category);

    Category toEntity(CategoryResponse response);

    Category toEntity(CategoryRequest request);

    void updateEntity(CategoryRequest request, @MappingTarget Category category);

    @Mapping(target = "subcategories", source = "subcategories")
    @Mapping(target = "subcategoryCount", expression = "java(category.getSubcategories().size())")
    CategoryWithSubcategoryResponse toCategoryWithSubcategoryResponse(Category category);

    default SubcategoryResponse toSubcategoryResponse(Category subcategory) {
        return new SubcategoryResponse(
                subcategory.getId(),
                subcategory.getName(),
                subcategory.getSubcategories().size(),
                subcategory.getProducts().size()
        );
    }

    default List<CategoryWithSubcategoryResponse> toCategoryWithSubcategoryResponseList(List<Category> categories) {
        return categories.stream().map(this::toCategoryWithSubcategoryResponse).collect(Collectors.toList());
    }
}
