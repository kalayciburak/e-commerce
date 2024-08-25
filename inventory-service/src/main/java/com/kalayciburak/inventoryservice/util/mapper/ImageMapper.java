package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.commonpackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.request.ImageRequest;
import com.kalayciburak.inventoryservice.model.dto.response.ImageResponse;
import com.kalayciburak.inventoryservice.model.entitiy.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ImageMapper extends BaseMapper<ImageResponse, Image> {
    @Mapping(target = "productId", source = "product.id")
    ImageResponse toResponse(Image image);

    @Mapping(target = "product.id", source = "productId")
    Image toEntity(ImageResponse imageResponse);

    @Mapping(target = "product.id", source = "productId")
    Image toEntity(ImageRequest request);

    @Mapping(target = "product.id", source = "productId")
    void updateEntity(ImageRequest request, @MappingTarget Image image);
}
