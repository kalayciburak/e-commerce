package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.commonpackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.request.AttributeRequest;
import com.kalayciburak.inventoryservice.model.dto.response.AttributeResponse;
import com.kalayciburak.inventoryservice.model.entitiy.Attribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AttributeMapper extends BaseMapper<AttributeResponse, Attribute> {
    @Mapping(target = "productId", source = "product.id")
    AttributeResponse toResponse(Attribute attribute);

    @Mapping(target = "product.id", source = "productId")
    Attribute toEntity(AttributeResponse attributeResponse);

    @Mapping(target = "product.id", source = "productId")
    Attribute toEntity(AttributeRequest request);

    @Mapping(target = "product.id", source = "productId")
    void updateEntity(AttributeRequest request, @MappingTarget Attribute attribute);
}
