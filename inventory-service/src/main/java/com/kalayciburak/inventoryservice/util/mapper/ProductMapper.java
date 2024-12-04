package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.commonpackage.event.inventory.ProductCreatedEvent;
import com.kalayciburak.commonpackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.request.ProductRequest;
import com.kalayciburak.inventoryservice.model.dto.response.product.ProductResponse;
import com.kalayciburak.inventoryservice.model.dto.response.product.ProductSimpleResponse;
import com.kalayciburak.inventoryservice.model.entitiy.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductResponse, Product> {
    ProductResponse toResponse(Product product);

    Product toEntity(ProductResponse response);

    ProductSimpleResponse toSimpleResponse(Product product);

    @Mapping(target = "category.id", source = "categoryId")
    Product toEntity(ProductRequest request);

    void updateEntity(ProductRequest request, @MappingTarget Product product);

    // ? Event mapping
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "categoryDescription", source = "category.description")
    ProductCreatedEvent toProductCreatedEvent(ProductSimpleResponse product);
}
