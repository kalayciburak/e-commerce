package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.commonpackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.request.ProductRequest;
import com.kalayciburak.inventoryservice.model.dto.response.ProductResponse;
import com.kalayciburak.inventoryservice.model.entitiy.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper extends BaseMapper<ProductResponse, Product> {
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    ProductResponse toResponse(Product product);

    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "category.name", source = "categoryName")
    Product toEntity(ProductResponse response);

    @Mapping(target = "category.id", source = "categoryId")
    Product toEntity(ProductRequest request);

    @Mapping(target = "category.id", source = "categoryId")
    void updateEntity(ProductRequest request, @MappingTarget Product product);
}
