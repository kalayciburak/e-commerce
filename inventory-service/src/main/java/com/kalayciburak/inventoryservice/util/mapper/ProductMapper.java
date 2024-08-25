package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.commonpackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.request.ProductRequest;
import com.kalayciburak.inventoryservice.model.dto.response.ProductResponse;
import com.kalayciburak.inventoryservice.model.entitiy.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductResponse, Product> {
    ProductResponse toResponse(Product product);

    Product toEntity(ProductResponse response);

    Product toEntity(ProductRequest request);

    void updateEntity(ProductRequest request, @MappingTarget Product product);
}
